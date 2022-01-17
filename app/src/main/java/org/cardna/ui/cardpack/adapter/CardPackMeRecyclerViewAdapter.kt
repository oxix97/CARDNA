package org.cardna.ui.cardpack.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.databinding.ItemCardpackCardmeBinding
import org.cardna.ui.maincard.DetailActivity

class CardPackMeRecyclerViewAdapter() :
    RecyclerView.Adapter<CardPackMeRecyclerViewAdapter.CardPackMeViewHolder>() {
    val cardList = mutableListOf<ResponseCardPackMeData>()

    inner class CardPackMeViewHolder(private val binding: ItemCardpackCardmeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardPackMeData) {
            binding.ivCarpackRecyclerview.setImageResource(R.drawable.dummy_img_cardpack_1)
            binding.tvCardpackRecyclerview.text = data.title
            binding.clRvItem.setOnClickListener {
                println(data.title)
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
        val intent = Intent(holder.itemView.context, DetailActivity::class.java)
        holder.onBind(cardList[position])
        intent.putExtra("id", cardList[position].id)
        intent.putExtra("tag", cardList[position].title)
        intent.putExtra("img", cardList[position].cardImg)
        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                intent
            )
        }
    }

    override fun getItemCount(): Int = cardList.size
}