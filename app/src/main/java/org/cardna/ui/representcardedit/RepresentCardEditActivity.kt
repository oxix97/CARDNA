package org.cardna.ui.representcardedit

import android.os.Bundle
import android.util.Log
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.maincard.MainCardList
import org.cardna.data.remote.model.maincard.RequestMainCardEditData
import org.cardna.databinding.ActivityRepresentCardEditBinding
import org.cardna.ui.maincard.adapter.RepresentCardListAdapter
import org.cardna.util.LinearGradientSpan
import org.cardna.util.SpacesItemDecoration2
import kotlin.math.roundToInt

class RepresentCardEditActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityRepresentCardEditBinding>
        (R.layout.activity_represent_card_edit) {
    private lateinit var representCardAdapter: RepresentCardListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initCoroutine()
    }



    override fun initView() {
        initCoroutine()
        setTextGradient()
        onClickResult()
    }


    //확인아이템들 리사이클러뷰에 연결결
    private fun initFragment(representList: MutableList<MainCardList>) {
        representCardAdapter = RepresentCardListAdapter()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvRepresentcardeditContainer.layoutManager = gridLayoutManager

        //representList에다가 cardList에서 id값 하나씩 추가
        val cardList = mutableListOf<Int>()
        representList.forEach {
            cardList.add(it.id)  //representList의 각 item의 id를 cardList에 하나씩 담는다 ->기존 representList의 id 값들만 저장한다
        }

        //representation card장식하는 거뿐임
        binding.rvRepresentcardeditContainer
            .addItemDecoration(
                SpacesItemDecoration2(
                    (4 * resources.displayMetrics.density).roundToInt(),
                    (4 * resources.displayMetrics.density).roundToInt(),
                )
            )
        // binding.rvRepresentcardeditContainer.addItemDecoration(SpacesItemDecoration(12))

        binding.rvRepresentcardeditContainer.adapter = representCardAdapter
        representCardAdapter.cardList = representList //대표카드 그려주는 adapter에게 representList를 넘김->id값 넘길필요 없으니까
        binding.tvRepresentcardeditCardListCount.text =
            "${representCardAdapter.itemCount}/7"  // representlist를 그려주는 adapter에서 대표카드 size를 넘겨받아서 위에 count값 교체
        activityReload()  //대표카드를 실시간으로 수정할수도 있으니까 실행
        onClick(cardList)  //바컴싯이 호출되면 대표카드 id값만 담은 cardList를 보낸다
        Log.d("대표카드들의 id: ", cardList.toString())
        representCardAdapter.notifyDataSetChanged()
    }


    private fun activityReload() {
        representCardAdapter.registerAdapterDataObserver(object :

        //리사이클러뷰에 있는 data를 observe할 수 있음
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                binding.tvRepresentcardeditCardListCount.text =  //지우고 남아있는 아이템 개수만큼 위에 표시함
                    "${representCardAdapter.itemCount}/7"
            }
        })
    }

    //바텀싯 호출문 -> cardList 보낸다 -> 대표카드의 아이디만 있는거
    private fun onClick(cardList: MutableList<Int>) {
        binding.fabRepresentcardedit.setOnClickListener {
            val bottomSheetDialog =
                RepresentCardEditBottomDialogFragment(cardList, representCardAdapter.cardList.size)
/*            cardList.forEach {
                println(it)
            }*/
            bottomSheetDialog.show(supportFragmentManager, "sdsfs")
        }
    }

    //텍스트 그라디언트
    private fun setTextGradient() {
        val text = binding.tvRepresentcardeditColorTitle.text.toString()
        val green = getColor(R.color.main_green)
        val purple = getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.tvRepresentcardeditColorTitle.text = spannable
    }

    private fun onClickResult() {
        binding.tvTvRepresentcardeditFinish.setOnClickListener {
            val cardList = representCardAdapter.cardList
            val cardIdList = mutableListOf<Int>()

            for (i in 0 until cardList.size) {
                cardIdList.add(cardList[i].id)
            }

            val data = RequestMainCardEditData(cardIdList)
            lifecycleScope.launch {
                try {
                    ApiService.cardService.putMainCardEdit(data)
                    ApiService.cardService.getUserMainCard()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            finish()
        }
    }


    private fun initCoroutine() {
        lifecycleScope.launch {
            try {
                val dataContainer = ApiService.cardService.getUserMainCard()
                val representList = dataContainer.data.mainCardList
                initFragment(representList)
            } catch (e: Exception) {
                Log.d("error", "error")
            }
        }
    }
}