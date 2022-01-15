package org.cardna.ui.cardpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.cardpack.ResponseOtherWriterData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ItemOtherwriteBinding
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter

class OtherWriteRecyclerViewAdapter(
    private val cardList: List<ResponseOtherWriterData>,
    private val clickListener: (ResponseOtherWriterData) -> Unit
) : ListAdapter<ResponseOtherWriterData, OtherWriteRecyclerViewAdapter.OtherWriterViewHolder>(diffUtil) {

    inner class OtherWriterViewHolder(private val binding: ItemOtherwriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseOtherWriterData) {
            binding.apply {
                tvOtherwriteTitle.text = data.title
                tvOtherwriteRelation.text = data.relation
                tvOtherwriteName.text = data.name
                root.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherWriteRecyclerViewAdapter.OtherWriterViewHolder {
        val binding = ItemOtherwriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherWriterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherWriteRecyclerViewAdapter.OtherWriterViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = cardList.size


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseOtherWriterData>() {
            override fun areContentsTheSame(oldItem: ResponseOtherWriterData, newItem: ResponseOtherWriterData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseOtherWriterData, newItem: ResponseOtherWriterData) =
                oldItem.id == newItem.id
        }
    }
}


