package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseOtherWriterData
import org.cardna.databinding.ActivityAlarmBinding
import org.cardna.ui.maincard.adapter.AlarmRecyclerViewAdapter

class   AlarmActivity : BaseViewUtil.BaseAppCompatActivity<ActivityAlarmBinding>(R.layout.activity_alarm) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
        val cardList = mutableListOf(
            ResponseOtherWriterData(1, "민우님이 소개를 적어줬어요", "마이노", "민우", "2022/01/21", true),
            ResponseOtherWriterData(1, "종찬님이 소개를 적어줬어요", "콜드벨", "종찬", "2022/01/21", true),
            ResponseOtherWriterData(1, "이솔님이 소개를 적어줬어요", "서버왕", "이솔", "2022/01/21", true),
            ResponseOtherWriterData(1, "다빈님이 소개를 적어줬어요", "다빈치", "다빈", "2022/01/21", true),
            ResponseOtherWriterData(1, "경민님이 소개를 적어줬어요", "에르메스", "경민", "2022/01/21", true),
            ResponseOtherWriterData(1, "남준님이 소개를 적어줬어요", "닝팝", "남준", "2022/01/21", true),
            ResponseOtherWriterData(1, "혜수님이 소개를 적어줬어요", "씨워러", "혜수", "2022/01/21", true),
            ResponseOtherWriterData(1, "혜원님이 소개를 적어줬어요", "워니버니", "혜원", "2022/01/21", true),
            ResponseOtherWriterData(1, "지우님이 소개를 적어줬어요", "쥬", "지우", "2022/01/21", true),
            ResponseOtherWriterData(1, "정아님이 소개를 적어줬어요", "루피", "정아", "2022/01/21", true),
            ResponseOtherWriterData(1, "정재님이 소개를 적어줬어요", "얌고", "정재", "2022/01/21", true),
            ResponseOtherWriterData(1, "희빈님이 소개를 적어줬어요", "에바홀리", "희빈", "2022/01/21", true),
            ResponseOtherWriterData(1, "민주님이 소개를 적어줬어요", "몰리", "민주", "2022/01/21", true)
        )


        val adpater = AlarmRecyclerViewAdapter(cardList) { position ->
            Intent(this, DetailCardMeActivity::class.java).apply {
                putExtra("name", position.name)
                putExtra("image", position.relation)
                putExtra("bio", position.createdAt)
                startActivity(this)
            }
        }

        with(binding) {
            rvAlarmList.adapter = adpater
            binding.rvAlarmList.layoutManager = LinearLayoutManager(this@AlarmActivity)
            adpater.submitList(cardList) // 아이템 업데이트
        }
    }
}