package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.data.remote.model.maincard.MainCardList
import org.cardna.databinding.ItemRepresentEditCardBinding

class RepresentCardListAdapter() :
    RecyclerView.Adapter<RepresentCardListAdapter.RepresentCardViewHolder>() {
    var cardList = mutableListOf<MainCardList>()

    inner class RepresentCardViewHolder(private val binding: ItemRepresentEditCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MainCardList, position: Int) {
            binding.apply {
                //Glide 사용해서 이미지 사용 circleCrop()
                Glide
                    .with(itemView.context)
                    .load(data.cardImg)
                    .into(binding.ivRepresentcardeditlistImage)

                tvRepresentcardlistUserTag.text = data.title
                if (data.isMe) {
                    clRvItem.setBackgroundResource(R.drawable.rectangle_main_green_radius_10)
                } else {
                    clRvItem.setBackgroundResource(R.drawable.rectangle_main_purple_radius_10)
                }
                ivRepresentcardeditlistDelete.setOnClickListener {
                    cardList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
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
