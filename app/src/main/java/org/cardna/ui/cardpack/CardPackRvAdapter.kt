package org.cardna.ui.cardpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.cardpack.ResponseCardpackCardmeData
import org.cardna.databinding.ItemCardpackCardmeBinding

class CardPackRvAdapter : RecyclerView.Adapter<CardPackRvAdapter.CardPackViewHolder>() {

    // 서버에서 받아올
    val cardList = mutableListOf<ResponseCardpackCardmeData>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardPackViewHolder {
        val binding = ItemCardpackCardmeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CardPackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardPackViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size

    class CardPackViewHolder(private val binding: ItemCardpackCardmeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardpackCardmeData ) {
            // binding.ivCardpackRv.setImageResource(data.cardImg)
            binding.ivCardpackRv.setImageResource(R.drawable.dummy_img_cardpack_1)
            binding.tvCardpackRv.text = data.title
        }
    }
}