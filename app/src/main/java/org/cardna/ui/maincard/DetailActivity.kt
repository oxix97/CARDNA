package org.cardna.ui.maincard

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityDetailBinding

class DetailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    override fun initView() {
        getCardPackData()
    }

    private fun getCardPackData() {
        val cardImg = intent.getIntExtra("cardImg", 0)
        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")

        with(binding) {
            ivDetailcardImage.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)
            tvDetailcardUserName.text = id.toString()
            tvDetailcardAbout.text = title
        }
    }
}