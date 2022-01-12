package org.cardna.ui.maincard

import android.os.Bundle
import android.view.View
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.MainCardListData
import org.cardna.databinding.FragmentMainCardBinding

class MainCardFragment :
    BaseViewUtil.BaseFragment<FragmentMainCardBinding>(R.layout.fragment_main_card) {
    private lateinit var mainCardAdapter: MainCardAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            MainCardListData(
                R.drawable.book,
                R.color.representative_green,
                R.drawable.ic_main_mypage_checked,
                "책 좋아!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.representative_green,
                R.drawable.ic_main_mypage_checked,
                "책 좋아22!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.representative_green,
                R.drawable.ic_main_mypage_checked,
                "책 좋아333!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.representative_green,
                R.drawable.ic_main_mypage_checked,
                "책 좋아4444!!"
            ),
        )
        mainCardAdapter = MainCardAdapter()
        mainCardAdapter.cardList.addAll(fragmentList)
        binding.vpMaincardList.adapter = mainCardAdapter

    }

    override fun initView() {

    }
}