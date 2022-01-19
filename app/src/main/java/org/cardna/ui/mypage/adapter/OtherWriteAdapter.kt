package org.cardna.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.representcardedit.ResponseCardStorageData
import org.cardna.databinding.ItemOtherwriteBinding

class OtherWriteAdapter() :
    RecyclerView.Adapter<OtherWriteAdapter.OtherWriteViewHolder>() {
    var cardList = mutableListOf<ResponseCardStorageData.Data>()

    inner class OtherWriteViewHolder(private val binding: ItemOtherwriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseCardStorageData.Data) {
            binding.apply {
                tvOtherwriteTitle.text = data.title
                tvOtherwriteRelation.text = data.relation
                tvOtherwriteName.text = data.name
                tvOtherwriteCreatedAt.text = data.createdAt
                ivOtherwriteGallery.isVisible = data.isImage
            /*    clOtherwrite.setOnClickListener {
                    Toast.makeText(itemView.context, "$adapterPosition", Toast.LENGTH_SHORT).show()
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherWriteViewHolder {
        val binding =
            ItemOtherwriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherWriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherWriteViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size
}