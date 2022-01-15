package org.cardna.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityMainBinding
import org.cardna.databinding.ActivityOtherCardCreateBinding

class OtherCardCreateActivity
    : BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardCreateBinding>(R.layout.activity_other_card_create){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_card_create)
    }

    override fun initView() {

    }
}