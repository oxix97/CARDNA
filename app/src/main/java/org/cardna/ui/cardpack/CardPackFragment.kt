package org.cardna.ui.cardpack

import CardMeFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.CardpackCustomTablayoutBinding
import org.cardna.databinding.FragmentCardPackBinding
import org.cardna.ui.cardpack.adapter.CardPackTabLayoutAdapter

class CardPackFragment :
    BaseViewUtil.BaseFragment<FragmentCardPackBinding>(R.layout.fragment_card_pack) {

    private lateinit var cardPackTabLayoutAdapter: CardPackTabLayoutAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setMakeCardIvListener()
    }

    override fun initView() {
        initCardPackAdapter()
        initCardPackTabLayout()
    }

    private fun initCardPackAdapter() {
        val fragmentList = listOf(CardMeFragment(), CardYouFragment())

        cardPackTabLayoutAdapter = CardPackTabLayoutAdapter(this)
        cardPackTabLayoutAdapter.fragments.addAll(fragmentList)
        binding.vpCardpack.adapter = cardPackTabLayoutAdapter
    }

    private fun initCardPackTabLayout() {
        val tabLabel = listOf("카드나", "카드너")

        TabLayoutMediator(binding.tlCardpack, binding.vpCardpack) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()

        binding.tlCardpack.getTabAt(0)?.customView = createTabLayout("카드나")
        binding.tlCardpack.getTabAt(1)?.customView = createTabLayout("카드너")

        for (position: Int in 0..binding.tlCardpack.tabCount) {
            if (binding.tlCardpack.getTabAt(position) != null) {
                binding.tlCardpack.setPaddingRelative(0, 0, 0, 0)
            }
        }
    }

    private fun createTabLayout(tabName: String): View {
        val tabBinding: CardpackCustomTablayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.cardpack_custom_tablayout,
            null,
            false
        )

        with(tabBinding) {
            when (tabName) {
                "카드나" -> {
                    tvCardmeTab.text = tabName
                    isCardme = true
                    ivCardpackTab.setImageResource(R.drawable.selector_cardpack_tab_cardme)
                    viewCardpackLine.setBackgroundResource(R.drawable.selector_cardpack_tab_cardme_line)
                }

                "카드너" -> {
                    tvCardyouTab.text = tabName
                    isCardme = false
                    ivCardpackTab.setImageResource(R.drawable.selector_cardpack_tab_cardyou)
                    viewCardpackLine.setBackgroundResource(R.drawable.selector_cardpack_tab_cardyou_line)
                }
            }
        }
        return tabBinding.root
    }

    private fun setMakeCardIvListener() {
        binding.ivMakeCard.setOnClickListener {
            // 메인 액티비티의 함수를 실행만 해주면 됨
            (activity as MainActivity).showBottomDialogCardFragment()
        }
    }
}
