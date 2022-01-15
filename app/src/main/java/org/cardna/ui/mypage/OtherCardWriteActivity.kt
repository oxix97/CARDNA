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

}