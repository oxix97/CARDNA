package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.representcardedit.RepresentCardData
import org.cardna.databinding.ItemRepresentCardMeBinding

class RepresentBottomSheetCardMeAdapter :
    RecyclerView.Adapter<RepresentBottomSheetCardMeAdapter.CardMeViewHolder>() {
    private var lastRemovedIndex : Int = Int.MAX_VALUE
    private var itemClickListener : ((Int, RepresentCardData, Boolean) -> Int) ?= null
    val cardMeList = mutableListOf<RepresentCardData>()

    fun setItemClickListener(listener : ((Int, RepresentCardData, Boolean) -> Int)) {
        itemClickListener = listener
    }

    fun setLastRemovedIndex(index : Int) {
        lastRemovedIndex = index
    }

    inner class CardMeViewHolder(private val binding: ItemRepresentCardMeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepresentCardData, position: Int) {
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
    ): RepresentBottomSheetCardMeAdapter.CardMeViewHolder {
        val binding =
            ItemRepresentCardMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardMeViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: RepresentBottomSheetCardMeAdapter.CardMeViewHolder,
        position: Int
    ) {
        holder.onBind(cardMeList[position], position)
    }

    override fun getItemCount(): Int = cardMeList.size
}
