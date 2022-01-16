package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseOtherWriterData
import org.cardna.databinding.ActivityOtherWriteBinding
import org.cardna.ui.mypage.adapter.OtherWriteRecyclerViewAdapter
import org.cardna.ui.maincard.DetailCardMeActivity

class OtherWriteActivity : BaseViewUtil.BaseAppCompatActivity<ActivityOtherWriteBinding>(R.layout.activity_other_write) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
        val cardList = mutableListOf(
            ResponseOtherWriterData(1, "종잔", "칭구", "가빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "바보", "칭구", "나빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "민우", "칭구", "다빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "바보", "칭구", "라빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "다빈", "칭구", "마빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "천제", "칭구", "바빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "ㅎㅇ", "칭구", "사빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "ㅎㅇ", "칭구", "아빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "ㅎㅇ", "칭구", "자빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "ㅎㅇ", "칭구", "차빈", "2022/42/42", true),
            ResponseOtherWriterData(1, "ㅎㅇ", "칭구", "카빈", "2022/42/42", true),
        )


        val adpater = OtherWriteRecyclerViewAdapter(cardList) { position ->
            Intent(this, DetailCardMeActivity::class.java).apply {
                putExtra("name", position.name)
                putExtra("image", position.relation)
                putExtra("bio", position.createdAt)
                startActivity(this)
            }
        }

        with(binding) {
            rvOtherwriteList.adapter = adpater
            binding.rvOtherwriteList.layoutManager = LinearLayoutManager(this@OtherWriteActivity)
            adpater.submitList(cardList) // 아이템 업데이트
        }
    }
}