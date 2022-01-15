package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.api.RepresentCardMeData
import org.cardna.databinding.ItemRepresentCardMeBinding

class RepresentBottomSheetCardMeAdapter :
    RecyclerView.Adapter<RepresentBottomSheetCardMeAdapter.CardMeViewHolder>() {
    val cardMeList = mutableListOf<RepresentCardMeData>()
    val storage = mutableListOf<Int>()
    var count = 0

    inner class CardMeViewHolder(private val binding: ItemRepresentCardMeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepresentCardMeData) {
            binding.clRvItem.setBackgroundColor(data.backgroundColor)
            binding.ivCardpackRecyclerview.setImageResource(data.image)
            binding.tvCardpackRecyclerview.text = data.userTag
            binding.clRvItem.setOnClickListener {
                if (!data.isClicked) {
                    data.isClicked = true
                    storage.add(adapterPosition, data.image)
                    binding.tvRepresentcardCount.text = count.toString()
                    binding.tvRepresentcardCount.visibility = View.VISIBLE
                } else {
                    data.isClicked = false
                    storage.remove(adapterPosition)
                    binding.tvRepresentcardCount.text = count.toString()
                    binding.tvRepresentcardCount.visibility = View.GONE
                }
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
        holder.onBind(cardMeList[position])
    }

    override fun getItemCount(): Int = cardMeList.size
}
