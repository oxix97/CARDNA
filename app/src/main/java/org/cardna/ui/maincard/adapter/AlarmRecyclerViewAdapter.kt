package org.cardna.ui.maincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.cardpack.ResponseOtherWriterData
import org.cardna.databinding.ItemAlarmBinding
import org.cardna.databinding.ItemOtherwriteBinding
import org.cardna.ui.mypage.adapter.OtherWriteRecyclerViewAdapter

class AlarmRecyclerViewAdapter(
    private val cardList: List<ResponseOtherWriterData>,
    private val clickListener: (ResponseOtherWriterData) -> Unit
) : ListAdapter<ResponseOtherWriterData, AlarmRecyclerViewAdapter.AlarmViewHolder>(diffUtil) {

    inner class AlarmViewHolder(private val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root) {
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
    ): AlarmViewHolder {
        val binding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmRecyclerViewAdapter.AlarmViewHolder, position: Int) {
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