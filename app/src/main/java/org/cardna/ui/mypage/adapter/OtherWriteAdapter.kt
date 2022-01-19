package org.cardna.ui.mypage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.representcardedit.ResponseCardStorageData
import org.cardna.databinding.ItemOtherwriteBinding
import org.cardna.ui.maincard.DetailActivity

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
                clOtherwriteItem.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra("id", data.id)
                        putExtra("title", data.title)
                        putExtra("relation", data.relation)
                        putExtra("name", data.name)
                        putExtra("createAt", data.createdAt)
                        putExtra("isImage", data.isImage)
                    }
                    itemView.context.startActivity(intent)
                }
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