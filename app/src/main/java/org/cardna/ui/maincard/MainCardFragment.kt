package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.MainCardListData
import org.cardna.databinding.FragmentMainCardBinding
import org.cardna.ui.maincard.adapter.MainCardAdapter

class MainCardFragment :
    BaseViewUtil.BaseFragment<FragmentMainCardBinding>(R.layout.fragment_main_card) {
    private lateinit var mainCardAdapter: MainCardAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        moveRepresentCardEditActivity()
        moveDetailActivity()
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            MainCardListData(
                R.drawable.dummy_img_test,
                R.color.main_green,
                "책 좋아!!"
            ),
            MainCardListData(
                R.drawable.dummy_img_cardpack_1,
                R.color.main_green,
                "책 좋아22!!"
            ),
            MainCardListData(
                R.drawable.dummy_img_test,
                R.color.main_green,
                "책 좋아333!!"
            ),
            MainCardListData(
                R.drawable.book,
                R.color.main_green,
                "책 좋아4444!!"
            ),
        )

        val dpValue = 48;
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()
        binding.vpMaincardList.clipToPadding = false
        binding.vpMaincardList.offscreenPageLimit = margin / 2
        binding.vpMaincardList.setPadding(0, 0, margin, 0)

        mainCardAdapter = MainCardAdapter()
        mainCardAdapter.cardList.addAll(fragmentList)

        binding.vpMaincardList.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvMaincardPageCount.text =
                        "${position + 1}/${mainCardAdapter.cardList.size}"
                }
            })
        }

        binding.vpMaincardList.adapter = mainCardAdapter
    }

    private fun moveRepresentCardEditActivity() {
    }

    private fun moveDetailActivity() {
        binding.llMaincardEditLayout.setOnClickListener {
            val intent = Intent(requireActivity(), RepresentCardEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
    }
}