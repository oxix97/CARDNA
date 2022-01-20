package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityOtherCardCreateBinding
import org.cardna.util.initRootClickEvent

class OtherCardCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardCreateBinding>(R.layout.activity_other_card_create) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        checkIdAndName()
        checkEditTextLength()
        initRootClickEvent(binding.ctlOthercardcreateTop)
    }

    private fun checkIdAndName() {
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

    // 관계 작성 시에 버튼 클릭 가능
    private fun checkEditTextLength() {
        // 처음에는 작성 완료 버튼 클릭 안되게
        binding.btnOthercardcreateNext.isClickable = false;

        binding.etOthercardcreateRelation.addTextChangedListener {
            if (binding.etOthercardcreateRelation.length() > 0) {
                with(binding) {
                    btnOthercardcreateNext.isClickable = true
                    btnOthercardcreateNext.isEnabled = true;
                    btnOthercardcreateNext.setTextColor(resources.getColor(R.color.black))
                    btnOthercardcreateNext.setBackgroundResource(R.drawable.background_btn_cardyou_abled)
                }
            } else {
                with(binding) {
                    btnOthercardcreateNext.isClickable = false;
                    btnOthercardcreateNext.isEnabled = false;
                    btnOthercardcreateNext.setTextColor(resources.getColor(R.color.white_2))
                    btnOthercardcreateNext.setBackgroundResource(R.drawable.background_btn_card_disabled)
                }
            }

            binding.tvOthercardcreateCnt.text = "${binding.etOthercardcreateRelation.length()}/10"
        }
    }

    // editText 내용 있을 때, 다음 버튼 누를 수 있도록 버튼 enable
    // 누르면 그에 써진 그사람과의 관계 정보를 OtherCardWriteActivity에 전달?
}