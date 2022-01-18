package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityOtherCardCreateBinding

class OtherCardCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardCreateBinding>(R.layout.activity_other_card_create) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setListener()
    }

    private fun setListener() {

        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("id", 0)
        binding.tvOthercardcreateRelation.text = "당신은 ${name}님에게 어떤 사람인가요 ?"

        binding.btnOthercardcreateNext.setOnClickListener {
            // editText 값이 들어가있으면 버튼 클릭할 수 있도록

            var intent = Intent(this, OtherCardWriteActivity::class.java)
            intent.putExtra("relation", binding.etOthercardcreateRelation.text.toString())
            startActivity(intent)
        }
    }
    // editText 내용 있을 때, 다음 버튼 누를 수 있도록 버튼 enable
    // 누르면 그에 써진 그사람과의 관계 정보를 OtherCardWriteActivity에 전달?
}