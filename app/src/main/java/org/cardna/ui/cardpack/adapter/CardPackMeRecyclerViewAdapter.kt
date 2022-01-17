package org.cardna.ui.cardpack.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ItemCardpackCardmeBinding
import org.cardna.ui.maincard.DetailActivity

class CardPackMeRecyclerViewAdapter(
    private val cardList: List<ResponseCardPackMeData>,
    private val clickListener: (ResponseCardPackMeData) -> Unit
) :
    RecyclerView.Adapter<CardPackMeRecyclerViewAdapter.CardPackMeViewHolder>() {

    inner class CardPackMeViewHolder(private val binding: ItemCardpackCardmeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardPackMeData) {
            with(binding) {
                ivCarpackRecyclerview.setImageResource(R.drawable.dummy_img_cardpack_1)
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