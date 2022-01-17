package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.maincard.MainCardListData
import org.cardna.data.remote.model.maincard.ResponseMainCardData
import org.cardna.databinding.FragmentMainCardBinding
import org.cardna.ui.cardpack.CardPackFragment
import org.cardna.ui.maincard.adapter.MainCardAdapter
import org.cardna.ui.representcardedit.RepresentCardEditActivity
import org.cardna.util.LinearGradientSpan
import kotlin.math.roundToInt

class MainCardFragment :
    BaseViewUtil.BaseFragment<FragmentMainCardBinding>(R.layout.fragment_main_card) {
    private lateinit var fragmentList: List<MainCardListData>
    private lateinit var mainCardAdapter: MainCardAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter()
        initClickEventCardYou()
        setTextGradient()
        initClickEventCardMe()
    }

    private fun initAdapter() {
           fragmentList = listOf(
                MainCardListData(
                    1,
                    R.drawable.dummy_img_test,
                    true,
                    "책 좋아!!"
                ),
                MainCardListData(
                    2,
                    R.drawable.dummy_img_test,
                    false,
                    "책 좋아!!"
                ),
                MainCardListData(
                    3,
                    R.drawable.dummy_img_test,
                    true,
                    "책 좋아!!"
                ),
                MainCardListData(
                    4,
                    R.drawable.dummy_img_test,
                    false,
                    "책 좋아!!"
                ),
            )

        //fragmentList=데이터 리스트 넣기

        //RecyclerView 연결
        mainCardAdapter = MainCardAdapter(fragmentList) {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java).apply {
                putExtra("id", it.id)
                startActivity(this)
            }
        }

        binding.apply {
            setMainCard(mainCardAdapter)
            pageSelect()
        }
    }

    //메인카드 레이아웃 마진
    private fun setMainCard(pagerAdapter: MainCardAdapter) {
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

    //애니메이션
    private fun getPageTransformer(): ViewPager2.PageTransformer {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * resources.displayMetrics.density).roundToInt()))

        return compositePageTransformer
    }

    //페이지 트랜스포머
    private fun pageSelect() {
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


    //텍스트 그라디언트
    private fun setTextGradient() {
        val text = binding.tvMaincardGotoCardpack.text.toString()
        val green = requireActivity().getColor(R.color.main_green)
        val purple = requireActivity().getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.tvMaincardGotoCardpack.text = spannable
    }


    //결과값 리스트를 통해 레이아웃 그리기

    //내가 내 메인카드 볼떄 화면
    private fun initClickEventCardMe() {
        with(binding) {
            //카드팩 보러가기 버튼 없애기
            tvMaincardGotoCardpack.visibility = View.GONE
            ivMaincardGotoCardpackBackground.visibility = View.GONE

            //친구추가 버튼 없애기
            ibtnMaincardFriend.visibility = View.GONE

            //클릭 이벤트 달기
            llMaincardEditLayout.setOnClickListener {
                val intent = Intent(requireActivity(), RepresentCardEditActivity::class.java)
                startActivity(intent)
            }
            ibtnMaincardAlarm.setOnClickListener {
                val intent = Intent(requireActivity(), AlarmActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //타인이 내 메인카드 볼때 화면
    private fun initClickEventCardYou() {
        var id: Int
        var name = ""
        if (getArguments() != null) {
            name = getArguments()?.getString("name") ?: ""
            binding.tvMaincardUserName.setText(name + "님은")
        }
        //넘어온 user id에 따른 대표카드 list보여주기
        //fun requestFriendMainCard

        with(binding) {
            //카드팩 보러가기 버튼 보이기
            tvMaincardGotoCardpack.visibility = View.VISIBLE
            ivMaincardGotoCardpackBackground.visibility = View.VISIBLE


            //친구추가 아이콘 보이기
            ibtnMaincardFriend.visibility = View.VISIBLE

            //카드 작성하기 아이콘 보이기
            ibtnMaincardAlarm.setImageResource(R.drawable.ic_mypage_write)
        }
        //클릭 이벤트 달기
        binding.tvMaincardGotoCardpack.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(org.cardna.R.id.fcv_main, CardPackFragment())
            transaction.commit()
        }
    }
}