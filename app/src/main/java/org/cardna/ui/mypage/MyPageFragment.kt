package org.cardna.ui.mypage

import CardMeFragment
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.FragmentMyPageBinding
import org.cardna.databinding.ItemMypageFriendBinding
import org.cardna.ui.cardpack.CardYouFragment
import org.cardna.ui.cardpack.adapter.CardPackMeRecyclerViewAdapter
import org.cardna.ui.cardpack.adapter.CardPackTabLayoutAdapter
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter
import org.cardna.util.SpacesItemDecoration

class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initScrollView()
        initCardMeRvAdapter()
/*        initRecyclerViewAdapter(
            listOf(
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕")
            )
        )*/
    }

    private fun initScrollView() {
        binding.scMypage.run {
            header = binding.ctlMypageHeader
        }
    }

    private fun initCardMeRvAdapter() {
        val myPageFriendAdapter = MyPageFriendAdapter(
            listOf(
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕"),
                ResponseMyPageFriendData(1, "다빈,", "유저네임", "안녕")
            )
        )

        binding.rvMypage.adapter = myPageFriendAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMypage.layoutManager = gridLayoutManager

        binding.rvMypage.addItemDecoration(SpacesItemDecoration(12))
        myPageFriendAdapter.notifyDataSetChanged()
    }
}