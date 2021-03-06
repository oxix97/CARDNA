package org.cardna.ui.mypage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ItemMypageFriendBinding

class MyPageFriendAdapter(
    private val friendList: List<ResponseMyPageFriendData>,
    private val clickListener: (ResponseMyPageFriendData) -> Unit
) : RecyclerView.Adapter<MyPageFriendAdapter.MyPageFriendViewHolder>() {

    inner class MyPageFriendViewHolder(private val binding: ItemMypageFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMyPageFriendData) {
            binding.apply {
                tvFriendName.text = data.name
                tvFriendSentence.text = data.sentence
                Glide.with(itemView.context).load(data.userImg).circleCrop().into(binding.ivFriendImage)
                root.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageFriendViewHolder {
        val binding = ItemMypageFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageFriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageFriendAdapter.MyPageFriendViewHolder, position: Int) {
        return holder.onBind(friendList[position])

    }

    override fun getItemCount(): Int = friendList.size
}