package org.cardna.ui.maincard.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.representedit.MainCardListData
import org.cardna.databinding.ItemMainCardViewBinding
import org.cardna.ui.maincard.DetailCardYouActivity

class MainCardAdapter : RecyclerView.Adapter<MainCardAdapter.MainCardViewHolder>() {

    val cardList = mutableListOf<MainCardListData>()

    inner class MainCardViewHolder(private val binding: ItemMainCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MainCardListData) {
            binding.ivMainCardImage.setImageResource(data.image)
            binding.tvMainCardTitle.text = data.tag

            if(data.isMe)
                binding.clMaincardContainer.setBackgroundResource(R.drawable.rectangle_green_null_black_radius_2)
            else
                binding.clMaincardContainer.setBackgroundResource(R.drawable.rectangle_right_null_black_radius_2)
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
        holder.itemView.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, DetailCardYouActivity::class.java)
            context.startActivity(intent)
        }
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}