package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.maincard.MainCardList
import org.cardna.databinding.FragmentMainCardBinding
import org.cardna.ui.cardpack.CardPackFragment
import org.cardna.ui.maincard.adapter.MainCardAdapter
import org.cardna.ui.mypage.OtherCardCreateActivity
import org.cardna.ui.mypage.OtherWriteActivity
import org.cardna.ui.representcardedit.RepresentCardEditActivity
import org.cardna.util.LinearGradientSpan
import kotlin.math.roundToInt

class MainCardFragment :
    BaseViewUtil.BaseFragment<FragmentMainCardBinding>(R.layout.fragment_main_card) {
    private lateinit var mainCardAdapter: MainCardAdapter
    private lateinit var list: MutableList<MainCardList>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initNetwork()
        setTextGradient()
    }

    //네트워크 통신으로 뿌려줄 리스트 초기화
    private fun initNetwork() {
        var id: Int?
        if (getArguments() != null) {
            id = getArguments()?.getInt("id", 0) ?: 0
            SeeOtherNetwork(id)
        } else {
            SeeMeNetwork()
        }
    }

    private fun SeeOtherNetwork(id: Int) {
        //초기유저 상단 데이터 뿌리는 통신
        lifecycleScope.launch {
            try {
                list = ApiService.cardService.getMainCard(id).data.mainCardList
                initAdapter(list)
                initClickEventCardYou(id)
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }

        //메인카드 리스트 뿌리는 통신
        lifecycleScope.launch {
            try {
                list = ApiService.cardService.getMainCard(id).data.mainCardList
                initAdapter(list)
                initClickEventCardYou(id)
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    private fun SeeMeNetwork() {
        lifecycleScope.launch {
            try {
                list = ApiService.cardService.getUserMainCard().data.mainCardList
                initAdapter(list)
                initClickEventCardMe()
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    private fun initAdapter(list: MutableList<MainCardList>) {
        mainCardAdapter = MainCardAdapter(list) {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java).apply {
                putExtra("id", it.id)
                startActivity(this)
            }
        }

        binding.apply {
            setAnswerPager(mainCardAdapter)
            pageCount()
        }
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

    //애니메이션
    private fun getPageTransformer(): ViewPager2.PageTransformer {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * resources.displayMetrics.density).roundToInt()))

        return compositePageTransformer
    }

    //페이지 트랜스포머
    private fun pageCount() {
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

    //내가 내 메인카드 볼떄 화면
    private fun initClickEventCardMe() {
        with(binding) {
            //카드팩 보러가기 버튼 없애기
            tvMaincardGotoCardpack.visibility = View.GONE
            ivMaincardGotoCardpackBackground.visibility = View.GONE

            //친구추가 버튼 없애기
            ctvMaincardFriend.visibility = View.GONE

            //연필->알림으로 바꾸기
            ibtnMaincardAlarm.setBackgroundResource(R.drawable.ic_alarm)

            //대표카드 수정 페이지로 이동
            llMaincardEditLayout.setOnClickListener {
                val intent = Intent(requireActivity(), RepresentCardEditActivity::class.java)
                startActivity(intent)
            }

            //알림 페이지로 이동
            ibtnMaincardAlarm.setOnClickListener {
                val intent = Intent(requireActivity(), AlarmActivity::class.java)
                startActivity(intent)
            }

        }
    }

    //타인이 내 메인카드 볼때 화면
    private fun initClickEventCardYou(id: Int) {
        var name = ""
        var sentence = ""
        if (getArguments() != null) {
            name = getArguments()?.getString("name") ?: ""
            sentence = getArguments()?.getString("sentence") ?: ""
            binding.tvMaincardUserName.setText(name + "님은")
            binding.tvMaincardTitle.setText(sentence)
        }
        with(binding) {
            //카드팩 보러가기 버튼 보이기
            tvMaincardGotoCardpack.visibility = View.VISIBLE
            ivMaincardGotoCardpackBackground.visibility = View.VISIBLE

            //edittext 버튼 없애기
            llMaincardEditLayout.visibility = View.INVISIBLE

            //친구추가 클릭
            ctvMaincardFriend.setOnClickListener {
                ctvMaincardFriend.toggle()
                //친구 추가하는 네트워크 통신
            }

            //카드너 작성하기 페이지로 이동
            ibtnMaincardAlarm.setOnClickListener {
                val intent = Intent(requireActivity(), OtherCardCreateActivity::class.java).apply {
                    //현재 사용자의 name값을 전달해줘야하나? 토큰으로 못가져오나..
                    putExtra("name", name)
                    putExtra("id", id)
                }
                startActivity(intent)
            }

            //카드팩 보러갈떄 유저 id bundle로 넘김
            tvMaincardGotoCardpack.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", id)

                val cardPackFragment = CardPackFragment()
                cardPackFragment.setArguments(bundle)

                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fcv_main, cardPackFragment)
                transaction.commit()
            }
        }
    }
}