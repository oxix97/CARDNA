package org.cardna.ui.cardpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateBinding
import org.cardna.databinding.ActivityCardCreateCompleteBinding

class CardCreateCompleteActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateCompleteBinding>(R.layout.activity_card_create_complete) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun initView() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        // MainActivity를 실행할 텐데 이게 새로 생성되는게 아니라, 살아있다면 유지되도록
        startActivity(intent)
    }

    // 여기서 intent를 이용해서 MainActivity로
}