package org.cardna.ui.mypage

import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityOtherCardCreateBinding

class OtherCardCreateActivity
    : BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardCreateBinding>(R.layout.activity_other_card_create){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_card_create)
    }

    override fun initView() {

    }

    // editText 내용 있을 때, 다음 버튼 누를 수 있도록 버튼 enable
    // 누르면 그에 써진 그사람과의 관계 정보를 OtherCardWriteActivity에 전달?
}