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
        initView()
    }

    override fun initView() {
        initAdapter()
        friendMainView()
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            MainCardListData(
                R.drawable.book,
                R.color.main_green,
                "책 좋아!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.main_green,
                "책 좋아22!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.main_green,
                "책 좋아333!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.main_green,
                "책 좋아4444!!"
            ),
        )
        mainCardAdapter = MainCardAdapter()
        mainCardAdapter.cardList.addAll(fragmentList)
        //해당 부분 모름 0 / 4
        binding.tvMaincardPageCount.text =
            "${mainCardAdapter.cardList.size} / ${mainCardAdapter.itemCount}"
        binding.vpMaincardList.adapter = mainCardAdapter
    }

    private fun friendMainView() {
        var id: Int
        var name = ""
        if (getArguments() != null) {
            name = getArguments()?.getString("name") ?: ""
            binding.tvMaincardUserName.setText(name + "님은")
        }
        //넘어온 user id에 따른 대표카드 list보여주기
        //fun requestFriendMainCard
    }
}