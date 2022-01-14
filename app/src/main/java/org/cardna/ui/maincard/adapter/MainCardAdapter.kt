package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.api.MainCardListData
import org.cardna.databinding.ItemMainCardViewBinding

class MainCardAdapter : RecyclerView.Adapter<MainCardAdapter.MainCardViewHolder>() {
    val cardList = mutableListOf<MainCardListData>()

    inner class MainCardViewHolder(private val binding: ItemMainCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MainCardListData) {
            binding.ivMainCardImage.setImageResource(data.image)
            binding.tvMainCardTitle.text = data.tag
            binding.clMaincardContainer.resources.getColor(data.color)
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