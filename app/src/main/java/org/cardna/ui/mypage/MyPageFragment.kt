package org.cardna.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentMyPageBinding

class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initScrollView()
    }

    fun initScrollView() {
        binding.scMypage.run {
            header = binding.ctlMypageHeader
        }
    }
}