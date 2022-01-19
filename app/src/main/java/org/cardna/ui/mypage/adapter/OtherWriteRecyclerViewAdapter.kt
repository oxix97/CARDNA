package org.cardna.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.mypage.ResponseCardYouBoxDataList
import org.cardna.databinding.ItemOtherwriteBinding

class OtherWriteRecyclerViewAdapter(
    private val cardList: MutableList<ResponseCardYouBoxDataList>,
    private val clickListener: (ResponseCardYouBoxDataList) -> Unit
) : ListAdapter<ResponseCardYouBoxDataList, OtherWriteRecyclerViewAdapter.OtherWriterViewHolder>(
    diffUtil
) {
    inner class OtherWriterViewHolder(private val binding: ItemOtherwriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardYouBoxDataList) {
            binding.apply {
                tvOtherwriteTitle.text = data.title
                tvOtherwriteRelation.text = data.relation
                tvOtherwriteName.text = data.name
                ivOtherwriteGallery.isVisible = data.isImage
                root.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherWriterViewHolder {
        val binding =
            ItemOtherwriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherWriterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherWriterViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = cardList.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseCardYouBoxDataList>() {
            override fun areContentsTheSame(
                oldItem: ResponseCardYouBoxDataList,
                newItem: ResponseCardYouBoxDataList,
            ) =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem:ResponseCardYouBoxDataList,
                newItem:ResponseCardYouBoxDataList,
            ) =
                oldItem.id == newItem.id
        }
    }
}


