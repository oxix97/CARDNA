package org.cardna.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ItemMypageFriendBinding

class MyPageFriendAdapter(
    private val friendList: List<ResponseMyPageFriendData>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<MyPageFriendAdapter.MyPageFriendViewHolder>() {

    inner class MyPageFriendViewHolder(private val binding: ItemMypageFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMyPageFriendData, clickListener: (Int) -> Unit, position: Int) {
            binding.ivFriendImage.setImageResource(data.userImg)
            binding.tvFriendName.text = data.name
            binding.tvFriendSentence.text = data.sentence
            binding.root.setOnClickListener {
                clickListener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageFriendViewHolder {
        val binding = ItemMypageFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageFriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageFriendAdapter.MyPageFriendViewHolder, position: Int) {
        return holder.onBind(friendList[position], clickListener, position)

    }

    override fun getItemCount(): Int = friendList.size
}