package org.cardna.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.FragmentMyPageBinding
import org.cardna.ui.maincard.MainCardFragment
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter
import org.cardna.util.SpacesItemDecoration


class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(org.cardna.R.layout.fragment_my_page) {

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
                ResponseMyPageFriendData(1, "다빈", org.cardna.R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "라빈", org.cardna.R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "마빈", org.cardna.R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "바빈", org.cardna.R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "사빈", org.cardna.R.drawable.img_friend_image, "하이"),
                ResponseMyPageFriendData(1, "아빈", org.cardna.R.drawable.img_friend_image, "하이")
            )
        ) { item ->
            val bundle = Bundle()
            bundle.putInt("userImg", item.userImg)
            bundle.putString("name", item.name)
            bundle.putString("sentence", item.sentence)

            val mainCardFragment = MainCardFragment()
            mainCardFragment.setArguments(bundle)

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(org.cardna.R.id.fcv_main, mainCardFragment)
            transaction.commit()
        }
        
        binding.rvMypage.adapter = myPageFriendAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMypage.layoutManager = gridLayoutManager

        binding.rvMypage.addItemDecoration(SpacesItemDecoration(12))
        myPageFriendAdapter.notifyDataSetChanged()
    }
}