package org.cardna.ui.cardpack

import CardMeFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.databinding.CardpackCustomTablayoutBinding
import org.cardna.databinding.FragmentCardPackBinding
import org.cardna.ui.cardpack.adapter.CardPackTabLayoutAdapter

class CardPackFragment :
    BaseViewUtil.BaseFragment<FragmentCardPackBinding>(R.layout.fragment_card_pack) {

    private lateinit var cardPackTabLayoutAdapter: CardPackTabLayoutAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        initCardPackAdapter()
        initCardPackTabLayout()
        setMakeCardIvListener()
    }

    private fun initCardPackAdapter() {
        val fragmentList: List<Fragment>

        //타인이 접근
        var id: Int?
        if (getArguments() != null) {
            id = getArguments()?.getInt("id", 4) ?: 0
            Log.d("idid", id.toString())
            // 각 아이디를 프래그먼트 생성할 때 전달해줘야 함
            val bundle = Bundle()
            bundle.putInt("id", id)

            val cardMeFragment = CardMeFragment()
            val cardYouFragment = CardYouFragment()

            cardMeFragment.setArguments(bundle)
            cardYouFragment.setArguments(bundle)


            fragmentList = listOf(cardMeFragment, cardYouFragment)

            //내가 접근->걍 통신하면됨
        } else {
            Log.d("idid띄면안됨", "타인이 접근하지 않음")
            fragmentList = listOf(CardMeFragment(), CardYouFragment())
        }


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
                    initCardMeLayout()
                }

                "카드너" -> {
                    tvCardyouTab.text = tabName
                    isCardme = false
                    ivCardpackTab.setImageResource(R.drawable.selector_cardpack_tab_cardyou)
                    viewCardpackLine.setBackgroundResource(R.drawable.selector_cardpack_tab_cardyou_line)
                    initCardYouLayout()
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

    //카드 총 개수 세팅
    private fun initCardMeLayout() {
        lifecycleScope.launch {
            val totalCardCnt = ApiService.cardService.getCardMe().data.totalCardCnt
            withContext(Dispatchers.Main) {
                binding.tvCardpackCnt.text = totalCardCnt.toString()
            }
        }
    }

    private fun initCardYouLayout() {
        lifecycleScope.launch {
            val totalCardCnt = ApiService.cardService.getCardYou().data.totalCardCnt
            withContext(Dispatchers.Main) {
                binding.tvCardpackCnt.text = totalCardCnt.toString()
            }
        }
    }
}
