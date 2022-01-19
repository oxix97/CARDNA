package org.cardna.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.data.remote.model.mypage.ResponseCardYouBoxData
import org.cardna.databinding.ItemOtherwriteBinding

class OtherWriteRecyclerViewAdapter(
    private val cardList: List<ResponseCardYouBoxData.Data>,
    private val clickListener: (ResponseCardYouBoxData.Data) -> Unit
) : ListAdapter<ResponseCardYouBoxData.Data, OtherWriteRecyclerViewAdapter.OtherWriterViewHolder>(
    diffUtil
) {

    inner class OtherWriterViewHolder(private val binding: ItemOtherwriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardYouBoxData.Data) {
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
        val diffUtil = object : DiffUtil.ItemCallback<ResponseCardYouBoxData.Data>() {
            override fun areContentsTheSame(
                oldItem: ResponseCardYouBoxData.Data,
                newItem: ResponseCardYouBoxData.Data
            ) =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: ResponseCardYouBoxData.Data,
                newItem: ResponseCardYouBoxData.Data
            ) =
                oldItem.id == newItem.id
        }
    }
}


