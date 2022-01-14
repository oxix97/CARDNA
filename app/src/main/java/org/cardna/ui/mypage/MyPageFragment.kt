package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.FragmentMyPageBinding
import org.cardna.ui.maincard.MainCardFragment
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter
import org.cardna.util.SpacesItemDecoration
import org.cardna.util.shortToast


class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(org.cardna.R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initScrollView()
        myPageRecyclerViewAdapter()
        initClickEvent()
    }

    private fun initScrollView() {
        binding.scMypage.run {
            header = binding.ctlMypageHeader
        }
    }

    private fun myPageRecyclerViewAdapter() {
        val myPageFriendAdapter = MyPageFriendAdapter(
            listOf(
                ResponseMyPageFriendData(1, "다빈", org.cardna.R.drawable.img_searchemail_friend_image, "하이"),
                ResponseMyPageFriendData(1, "라빈", org.cardna.R.drawable.img_searchemail_friend_image, "하이"),
                ResponseMyPageFriendData(1, "마빈", org.cardna.R.drawable.img_searchemail_friend_image, "하이"),
                ResponseMyPageFriendData(1, "바빈", org.cardna.R.drawable.img_searchemail_friend_image, "하이"),
                ResponseMyPageFriendData(1, "사빈", org.cardna.R.drawable.img_searchemail_friend_image, "하이"),
                ResponseMyPageFriendData(1, "아빈", org.cardna.R.drawable.img_searchemail_friend_image, "하이")
            )
        ) { item ->
            val bundle = Bundle()
            bundle.putInt("id", item.id)
            bundle.putString("name", item.name)

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

    private fun initClickEvent() {
        with(binding) {

            etMypageSearchBackground.setOnEditorActionListener { textView, action, event ->
                var handled = false
                if (action == EditorInfo.IME_ACTION_DONE) {
                    //검색완료 키보드 이벤트 완료 시 리사이클러뷰 item 새로 띄우기
                    requireContext().shortToast("test")
                    handled = true
                }
                handled
            }

            ivMypageGotoEmailsearch.setOnClickListener {
                startActivity(Intent(requireContext(), SearchEmailActivity::class.java))
            }
            ivMypageOtherWrote.setOnClickListener {
                startActivity(Intent(requireContext(), OtherWriteActivity::class.java))
            }
            ivMypageSetting.setOnClickListener {
                startActivity(Intent(requireContext(), SettingActivity::class.java))
            }
        }
    }
}