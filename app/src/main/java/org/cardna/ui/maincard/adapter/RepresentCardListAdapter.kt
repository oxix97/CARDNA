package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.api.RepresentCardData
import org.cardna.databinding.ItemRepresentEditCardBinding

class RepresentCardListAdapter :
    RecyclerView.Adapter<RepresentCardListAdapter.RepresentCardViewHolder>() {
    val cardList = mutableListOf<RepresentCardData>()

    inner class RepresentCardViewHolder(private val binding: ItemRepresentEditCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepresentCardData, position: Int) {
            binding.ivRepresentcardeditlistImage.setImageResource(data.image)
            binding.tvRepresentcardlistUserTag.text = data.userTag
            binding.clRvItem.setBackgroundResource(data.backgroundColor)
            binding.ivRepresentcardeditlistDelete.setOnClickListener {
                notifyItemRemoved(adapterPosition)
                cardList.removeAt(adapterPosition + 1)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepresentCardViewHolder {
        val binding =
            ItemRepresentEditCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepresentCardViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RepresentCardViewHolder,
        position: Int
    ) {
        holder.onBind(cardList[position], position)
    }

    override fun getItemCount(): Int = cardList.size
}
