package org.cardna.ui.maincard

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
            binding.ivMainCard.setImageResource(data.image)
            binding.ivCardMeIcon.setImageResource(data.icon)
            binding.tvUserTag.text = data.tag
            binding.llUserTagContainer.resources.getColor(data.color)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainCardAdapter.MainCardViewHolder {
        val binding =
            ItemMainCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainCardAdapter.MainCardViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}