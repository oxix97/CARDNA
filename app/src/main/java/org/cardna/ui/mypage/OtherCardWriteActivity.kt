package org.cardna.ui.mypage

import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityOtherCardWriteBinding

class OtherCardWriteActivity
    : BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardWriteBinding>(R.layout.activity_other_card_write) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_card_write)
    }

    override fun initView() {
    }


    // OtherCardCreateActivity에서 넘어오면 바텀씻 띄워서 이미지까지 받고, 작성완료 하면
    //
    // 타인과의 관계, 이미지, 제목, 내용 정보 가지고 데이터 어디에 전달 ?


    // CardCreateActivity랑 비슷하게 코드 짜기
}