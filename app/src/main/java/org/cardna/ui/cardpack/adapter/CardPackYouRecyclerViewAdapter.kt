package org.cardna.ui.cardpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.cardpack.ResponseCardPackYouData
import org.cardna.databinding.ItemCardpackCardyouBinding

class CardPackYouRecyclerViewAdapter : RecyclerView.Adapter<CardPackYouRecyclerViewAdapter.CardPackYouViewHolder>() {

    val cardList = mutableListOf<ResponseCardPackYouData>()

    inner class CardPackYouViewHolder(private val binding: ItemCardpackCardyouBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardPackYouData ) {
            binding.ivCardpackRecyclerview.setImageResource(R.drawable.dummy_img_cardpack_1)
            binding.tvCardpackRecyclerview.text = data.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardPackYouViewHolder {
        val binding = ItemCardpackCardyouBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardPackYouViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardPackYouViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}