package org.cardna.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.mypage.ResponseCardStorageData
import org.cardna.databinding.ItemOtherwriteBinding

class OtherWriteRecyclerViewAdapter(
    private val cardList: MutableList<ResponseCardStorageData.Data>,
    private val clickListener: (ResponseCardStorageData.Data) -> Unit
) : ListAdapter<ResponseCardStorageData.Data, OtherWriteRecyclerViewAdapter.OtherWriterViewHolder>(
    diffUtil
) {
    inner class OtherWriterViewHolder(private val binding: ItemOtherwriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardStorageData.Data) {
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
        val diffUtil = object : DiffUtil.ItemCallback<ResponseCardStorageData.Data>() {
            override fun areContentsTheSame(
                oldItem: ResponseCardStorageData.Data,
                newItem: ResponseCardStorageData.Data,
            ) =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: ResponseCardStorageData.Data,
                newItem: ResponseCardStorageData.Data,
            ) =
                oldItem.id == newItem.id
        }
    }
}


