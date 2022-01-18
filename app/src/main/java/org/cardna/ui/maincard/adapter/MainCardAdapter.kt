package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.data.remote.model.maincard.MainCardList
import org.cardna.databinding.ItemMainCardViewBinding

class MainCardAdapter(
    val cardList: MutableList<MainCardList>,
    private val clickListener: (MainCardList) -> Unit
) : RecyclerView.Adapter<MainCardAdapter.MainCardViewHolder>() {

    inner class MainCardViewHolder(private val binding: ItemMainCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MainCardList) {
            //상단 이름, 타이틀, 이미지
            Glide.with(itemView.context).load(data.cardImg).into(binding.ivMainCardImage)
            binding.tvMainCardTitle.text = data.title

            //카드 너, 나에 따라 데이터 색상값 변경
            if (data.isMe)
                binding.clMaincardContainer.setBackgroundResource(R.drawable.rectangle_green_null_black_radius_2)
            else
                binding.clMaincardContainer.setBackgroundResource(R.drawable.rectangle_right_null_black_radius_2)

            binding.root.setOnClickListener {
                clickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainCardViewHolder {
        val binding =
            ItemMainCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainCardViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}