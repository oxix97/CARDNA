package org.cardna.ui.cardpack

import CardMeFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
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
import org.cardna.ui.mypage.OtherCardCreateActivity

class CardPackFragment : BaseViewUtil.BaseFragment<FragmentCardPackBinding>(R.layout.fragment_card_pack) {

    private lateinit var cardPackTabLayoutAdapter: CardPackTabLayoutAdapter
    private val cardPackViewModel: CardPackViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        if (cardPackViewModel.id == null) {
            val fragmentList: List<Fragment>
            fragmentList = listOf(CardMeFragment(), CardYouFragment())
            initCardMeLayout()

            cardPackTabLayoutAdapter = CardPackTabLayoutAdapter(this)
            cardPackTabLayoutAdapter.fragments.addAll(fragmentList)
            binding.vpCardpack.adapter = cardPackTabLayoutAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    override fun initView() {
        initCardPackAdapter()
        initCardPackTabLayout()
    }

    private fun initViewModel() {
        binding.viewModel = cardPackViewModel
        binding.lifecycleOwner = this@CardPackFragment
    }

    private fun initCardPackAdapter() {
        val fragmentList: List<Fragment>
        var id: Int?
        var name: String?

        if (getArguments() != null) {
            id = getArguments()?.getInt("id", 4) ?: 0
            name = getArguments()?.getString("name") ?: ""

            cardPackViewModel.id = id

            val bundle = Bundle()
            bundle.putInt("id", id)
            bundle.putString("name", name)

            val cardMeFragment = CardMeFragment()
            val cardYouFragment = CardYouFragment()

            cardMeFragment.setArguments(bundle)
            cardYouFragment.setArguments(bundle)

            fragmentList = listOf(cardMeFragment, cardYouFragment)
            initCardYouLayout(id, name)
        } else {
            Log.d("내가 내메인카드", "내가내메인")
            fragmentList = listOf(CardMeFragment(), CardYouFragment())
            initCardMeLayout()
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
        setMakeCardIvListener()
    }

    private fun initCardYouLayout(id: Int, name: String) {
        lifecycleScope.launch {
            val totalCardCnt = ApiService.cardService.getOtherCardMe(id).data.totalCardCnt
            withContext(Dispatchers.Main) {
                binding.tvCardpackCnt.text = totalCardCnt.toString()
            }
        }
        with(binding) {
            ivMakeCard.setBackgroundResource(R.drawable.ic_mypage_write)
            ivMakeCard.setOnClickListener {
                val intent = Intent(requireActivity(), OtherCardCreateActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("name", name)
                }
                startActivity(intent)
            }
        }
    }
}
