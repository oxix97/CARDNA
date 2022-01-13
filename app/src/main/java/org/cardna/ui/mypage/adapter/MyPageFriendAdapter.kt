package org.cardna.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ItemCardpackCardmeBinding
import org.cardna.databinding.ItemMypageFriendBinding
import org.cardna.ui.cardpack.adapter.CardPackMeRecyclerViewAdapter

class MyPageFriendAdapter(var friendList: List<ResponseMyPageFriendData>) : RecyclerView.Adapter<MyPageFriendAdapter.MyPageFriendViewHolder>() {


    inner class MyPageFriendViewHolder(private val binding: ItemMypageFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMyPageFriendData) {
            binding.ivFriendImage.setImageResource(R.drawable.img_friend_image)
            binding.tvFriendName.text = data.name
            binding.tvFriendSentence.text = data.sentence
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