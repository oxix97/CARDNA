package org.cardna.ui.cardpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.data.remote.model.cardpack.CardMeList
import org.cardna.data.remote.model.cardpack.CardYouList
import org.cardna.data.remote.model.cardpack.ResponseCardAllData
import org.cardna.databinding.ItemCardpackCardmeBinding

class CardPackMeRecyclerViewAdapter(
// private val cardList: MutableList<CardMeList>,
    private val cardList: MutableList<CardMeList>,
    private val clickListener: (CardMeList) -> Unit
) :
    RecyclerView.Adapter<CardPackMeRecyclerViewAdapter.CardPackMeViewHolder>() {

    inner class CardPackMeViewHolder(private val binding: ItemCardpackCardmeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: CardMeList) {
            // if (data is CardMeList) {
            with(binding) {
                // uri로 받은 사진 Glide로 띄우기
                Glide.with(itemView.context).load(data.cardImg).into(binding.ivCardpackRecyclerview)
                tvCardpackRecyclerview.text = data.title
                root.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardPackMeViewHolder {
        val binding =
            ItemCardpackCardmeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardPackMeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardPackMeViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}