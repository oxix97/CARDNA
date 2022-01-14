package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.api.RepresentCardYouData
import org.cardna.databinding.ItemRepresentCardYouBinding

class RepresentBottomSheetCardYouAdapter :
    RecyclerView.Adapter<RepresentBottomSheetCardYouAdapter.CardYouViewHolder>() {
    val cardYouList = mutableListOf<RepresentCardYouData>()
    val storage = mutableListOf<Int>()
    var count = 0

    inner class CardYouViewHolder(private val binding: ItemRepresentCardYouBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepresentCardYouData) {
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
