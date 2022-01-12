package org.cardna.ui.cardpack

import CardMeFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentCardPackBinding

class CardPackFragment : BaseViewUtil.BaseFragment<FragmentCardPackBinding>(R.layout.fragment_card_pack) {

    private lateinit var cardPackTabLayoutAdapter: CardPackTabLayoutAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2와 Adapter 연동
        initCardPackAdapter()
        initCardPackTabLayout()
    }



    // TabLayout 설정
    private fun initCardPackAdapter() {
        val fragmentList = listOf(CardYouFragment(), CardMeFragment())

        // ViewPager2와 Adapter 연동
        cardPackTabLayoutAdapter = CardPackTabLayoutAdapter(this)
        cardPackTabLayoutAdapter.fragments.addAll(fragmentList)
        binding.vpCardPack.adapter = cardPackTabLayoutAdapter
    }

    private fun initCardPackTabLayout() {
        val tabLabel = listOf("카드나", "카드너")

        TabLayoutMediator(binding.tlCardPack, binding.vpCardPack) { tab, position ->
            tab.text = tabLabel[position]
            // 페이지 개수만큼 tab 생성
        }.attach()

        binding.tlCardPack.getTabAt(0)?.customView = createTabLayout("카드나")
        binding.tlCardPack.getTabAt(1)?.customView = createTabLayout("카드너")
        // binding.tlCardPack.getTabAt(1)?.setCustomView(createTabLayout("카드너"))

        for (position: Int in 0..binding.tlCardPack.tabCount) {
            if(binding.tlCardPack.getTabAt(position) != null){
                binding.tlCardPack.setPaddingRelative(0, 0, 0, 0)
            }
        }
    }

    private fun createTabLayout(tabName: String): View {
        var tabView = LayoutInflater.from(requireContext())
            .inflate(org.cardna.R.layout.cardpack_custom_tablayout, null)

        var tvTab = tabView.findViewById<TextView>(org.cardna.R.id.tv_cardpack_tab)
        tvTab.text = tabName

        when (tabName) {
            "카드나" -> {
                tvTab.setTextColor(org.cardna.R.drawable.selector_cardpack_tab_cardme_text)
                tabView.findViewById<ImageView>(org.cardna.R.id.iv_cardpack_tab).setImageResource(org.cardna.R.drawable.selector_cardpack_tab_cardme)
                tabView.findViewById<View>(org.cardna.R.id.view_cardpack_line).setBackgroundResource(org.cardna.R.drawable.selector_cardpack_tab_cardme_line)
            }

            "카드너" -> {
                tvTab.setTextColor(org.cardna.R.drawable.selector_cardpack_tab_cardyou_text)
                tabView.findViewById<ImageView>(org.cardna.R.id.iv_cardpack_tab).setImageResource(org.cardna.R.drawable.selector_cardpack_tab_cardyou)
                tabView.findViewById<View>(org.cardna.R.id.view_cardpack_line).setBackgroundResource(org.cardna.R.drawable.selector_cardpack_tab_cardyou_line)
            }
        }

        return tabView
    }


}
