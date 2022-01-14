package org.cardna.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.FragmentMyPageBinding
import org.cardna.ui.maincard.MainCardFragment
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter
import org.cardna.util.SpacesItemDecoration

class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initScrollView()
        myPageRecyclerViewAdapter()
    }

    private fun initScrollView() {
        binding.scMypage.run {
            header = binding.ctlMypageHeader
        }
    }

    private fun myPageRecyclerViewAdapter() {
        val myPageFriendAdapter = MyPageFriendAdapter(
            listOf(
                ResponseMyPageFriendData(1, "다빈", R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "라빈", R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "마빈", R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "바빈", R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "사빈", R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "아빈", R.drawable.img_friend_image, "하이")
            )
        ) { position ->
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fcv_main, MainCardFragment())
            transaction.commit()
        }

        binding.rvMypage.adapter = myPageFriendAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMypage.layoutManager = gridLayoutManager

        binding.rvMypage.addItemDecoration(SpacesItemDecoration(12))
        myPageFriendAdapter.notifyDataSetChanged()
    }
}