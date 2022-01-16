package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.api.RepresentCardData
import org.cardna.databinding.ItemRepresentCardYouBinding

class RepresentBottomSheetCardYouAdapter :
    RecyclerView.Adapter<RepresentBottomSheetCardYouAdapter.CardYouViewHolder>() {
    private var lastRemovedIndex : Int = Int.MAX_VALUE
    private var itemClickListener : ((Int, RepresentCardData, Boolean) -> Int) ?= null
    val cardYouList = mutableListOf<RepresentCardData>()

    fun setItemClickListener(listener : ((Int, RepresentCardData, Boolean) -> Int)) {
        itemClickListener = listener
    }

    fun setLastRemovedIndex(index : Int) {
        lastRemovedIndex = index
    }

    inner class CardYouViewHolder(private val binding: ItemRepresentCardYouBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepresentCardData) {
            binding.clRvItem.setBackgroundResource(data.backgroundColor)
            binding.ivCardpackRecyclerview.setImageResource(data.image)
            binding.tvCardpackRecyclerview.text = data.userTag

            binding.tvRepresentcardCount.isVisible = data.isClicked

            if(lastRemovedIndex < data.index) {
                data.index = data.index - 1
            }
            binding.tvRepresentcardCount.text = (data.index + 1).toString()
            binding.clRvItem.setOnClickListener {
                data.isClicked = !data.isClicked
                data.index = requireNotNull(itemClickListener?.invoke(data.index, data, data.isClicked))
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