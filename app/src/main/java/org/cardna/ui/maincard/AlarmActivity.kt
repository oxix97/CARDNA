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
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "가빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "나빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "다빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "라빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "마빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "바빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "사빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "아빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "자빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "차빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "알림왓숑", "칭구", "카빈", "2022/42/42", true),
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