package org.cardna.ui.cardpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.data.remote.model.cardpack.CardMeList
import org.cardna.data.remote.model.cardpack.CardYouList
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.data.remote.model.cardpack.ResponseCardPackYouData
import org.cardna.databinding.ItemCardpackCardmeBinding
import org.cardna.databinding.ItemCardpackCardyouBinding


class CardPackYouRecyclerViewAdapter(
    private val cardList: MutableList<CardYouList>,
    private val clickListener: (CardYouList) -> Unit
) :
    RecyclerView.Adapter<CardPackYouRecyclerViewAdapter.CardPackYouViewHolder>() {

    inner class CardPackYouViewHolder(private val binding: ItemCardpackCardyouBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CardYouList) {
            with(binding) {
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
    ): CardPackYouViewHolder {
        val binding =
            ItemCardpackCardyouBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardPackYouViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardPackYouRecyclerViewAdapter.CardPackYouViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}
