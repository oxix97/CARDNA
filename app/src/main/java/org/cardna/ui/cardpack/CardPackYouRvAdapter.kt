package org.cardna.ui.cardpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.cardpack.ResponseCardpackCardyouData
import org.cardna.databinding.ItemCardpackCardyouBinding

class CardPackYouRvAdapter : RecyclerView.Adapter<CardPackYouRvAdapter.CardPackYouViewHolder>() {
    // 서버에서 받아올
    val cardList = mutableListOf<ResponseCardpackCardyouData>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardPackYouViewHolder {
        val binding = ItemCardpackCardyouBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CardPackYouViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardPackYouViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size

    class CardPackYouViewHolder(private val binding: ItemCardpackCardyouBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardpackCardyouData ) {
            // binding.ivCardpackRv.setImageResource(data.cardImg)
            binding.ivCardpackRv.setImageResource(R.drawable.dummy_img_cardpack_1)
            binding.tvCardpackRv.text = data.title
        }
    }
}