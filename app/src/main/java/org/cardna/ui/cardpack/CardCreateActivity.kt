package org.cardna.ui.cardpack

import android.content.Intent
import android.os.Bundle
import android.widget.ScrollView
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateBinding

class CardCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateBinding>(R.layout.activity_card_create) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        //setListener()
        binding.svCardcreateTop.fullScroll(ScrollView.FOCUS_DOWN);
    }

    override fun initView() {

    }

/*    private fun setListener() {
        binding.btnCreateCard.setOnClickListener{
            val intent = Intent(this, CardCreateCompleteActivity::class.java)
            startActivity(intent)
        }
    }*/


}