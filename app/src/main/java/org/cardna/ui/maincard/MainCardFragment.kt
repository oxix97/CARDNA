package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.maincard.MainCardListData
import org.cardna.databinding.FragmentMainCardBinding
import org.cardna.ui.maincard.adapter.MainCardAdapter
import org.cardna.ui.representcardedit.RepresentCardEditActivity
import kotlin.math.abs
import kotlin.math.roundToInt

class MainCardFragment :
    BaseViewUtil.BaseFragment<FragmentMainCardBinding>(R.layout.fragment_main_card) {
    private lateinit var mainCardAdapter: MainCardAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        moveDetailActivity()
        friendMainView()
        moveAlarmActivity()


        mainCardAdapter = MainCardAdapter()


        val fragmentList = listOf(
            MainCardListData(
                R.drawable.dummy_img_test,
                true,
                "책 좋아!!"
            ),
            MainCardListData(
                R.drawable.dummy_img_cardpack_1,
                false,
                "책 좋아22!!"
            ),
            MainCardListData(
                R.drawable.dummy_img_test,
                true,
                "책 좋아333!!"
            ),
            MainCardListData(
                R.drawable.dummy_img_test,
                false,
                "책 좋아4444!!"
            ),
        )

        mainCardAdapter.cardList.addAll(fragmentList)
        setAnswerPager(mainCardAdapter)
        count()
    }


    private fun setAnswerPager(pagerAdapter: MainCardAdapter) {


        val compositePageTransformer = getPageTransformer()
        binding.vpMaincardList.apply {
            adapter = pagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            setPageTransformer(compositePageTransformer)

            setPadding(56, 0, 56, 0)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    //애니메이션인듯 ?
    private fun getPageTransformer(): ViewPager2.PageTransformer {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * resources.displayMetrics.density).roundToInt()))

        return compositePageTransformer
    }

    //페이지 트랜스포머
    fun count() {
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


    //상세 페이지로 이동
    private fun moveDetailActivity() {
        binding.llMaincardEditLayout.setOnClickListener {
            val intent = Intent(requireActivity(), RepresentCardEditActivity::class.java)
            startActivity(intent)
        }
    }

    //알림 페이지로 이동
    private fun moveAlarmActivity() {
        binding.ibtnMaincardAlarm.setOnClickListener {
            val intent = Intent(requireActivity(), AlarmActivity::class.java)
            startActivity(intent)
        }
    }

    //타인이 접근
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