package org.cardna.ui.cardpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.cardpack.ResponseCardpackCardmeData
import org.cardna.databinding.ItemCardpackCardmeBinding

class CardPackRvAdapter : RecyclerView.Adapter<CardPackRvAdapter.> {

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


    class CardPackViewHolder(private val binding: ItemCardpackCardmeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ) {
            binding.ivCardpackRv.setImageResource(data.)
            binding.tvCardpackRv.text = data.
        }
    }
}