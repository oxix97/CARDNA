package org.cardna.ui.maincard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.data.remote.model.representcardedit.UpdateData
import org.cardna.databinding.ItemRepresentCardYouBinding

class RepresentBottomSheetCardYouAdapter(
    private val isSelectedCount: Int,
    private val representCardList: MutableList<Int>
) :
    RecyclerView.Adapter<RepresentBottomSheetCardYouAdapter.CardYouViewHolder>() {
    private var lastRemovedIndex: Int = Int.MAX_VALUE
    private var itemClickListener: ((Int, UpdateData, Boolean) -> Int)? = null

    val cardYouList = mutableListOf<UpdateData>()
    val pickUpList = mutableListOf<UpdateData>()

    fun setItemClickListener(listener: ((Int, UpdateData, Boolean) -> Int)) {
        itemClickListener = listener
    }

    fun setLastRemovedIndex(index: Int) {
        lastRemovedIndex = index
    }

    inner class CardYouViewHolder(private val binding: ItemRepresentCardYouBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UpdateData) {
            binding.clRvItem.setBackgroundResource(R.drawable.rectangle_main_purple_radius_10)
            Glide
                .with(itemView.context)
                .load(data.cardImg)
                .into(binding.ivCardpackRecyclerview)
            binding.tvCardpackRecyclerview.text = data.title
            binding.tvRepresentcardCount.isVisible = data.isClicked

            if (lastRemovedIndex < data.index) {
                data.index = data.index - 1
            }
            binding.tvRepresentcardCount.text = (data.index + 1).toString()
            binding.clRvItem.setOnClickListener {
                data.isClicked = !data.isClicked
                data.index =
                    requireNotNull(itemClickListener?.invoke(data.index, data, data.isClicked))

                if (data.isClicked) {
                    pickUpList.add(data)
                    Log.d("cardYou ID : ", data.id.toString())
                } else {
                    pickUpList.removeAt(pickUpList.indexOf(data))
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepresentBottomSheetCardYouAdapter.CardYouViewHolder {
        val binding =
            ItemRepresentCardYouBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardYouViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RepresentBottomSheetCardYouAdapter.CardYouViewHolder,
        position: Int
    ) {
        holder.onBind(cardYouList[position])
    }

    override fun getItemCount(): Int = cardYouList.size
}