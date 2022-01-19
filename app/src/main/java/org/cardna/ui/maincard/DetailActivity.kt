package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityDetailBinding
import org.cardna.ui.cardpack.CardCreateActivity
import org.cardna.ui.cardpack.CardCreateCompleteActivity

class DetailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    override fun initView() {
        getCardPackData()
        setListener()
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


    // 카드너 만들기 버튼 누르면,
    private fun setListener(){
        binding.btnDetailCardCreate.setOnClickListener{

            // 서버로 카드너 만들기 호출



            // 인텐트로 CardCreateCompleteActivity로 이동
            val intent = Intent(this@DetailActivity, CardCreateCompleteActivity::class.java)
            intent.putExtra("meOrYou", CARD_YOU) // 카드나 추가이므로 CARD_YOU를 보내줌 // 심볼 - 2, 갤러리 - null
            intent.putExtra("symbolId", -1) // CompleteActivity에서 -1을 받아서, 심볼이 아닌 이미지를 띄워줘야 하므로
            // 카드의 이미지 intent.putExtra("cardImg", uri.toString()) // 심볼 - null, 갤러리 - adflkadlfaf
            // 카드의 title 넣어주기 intent.putExtra("cardTitle", binding.etCardcreateKeyword.text.toString())
            startActivity(intent)
        }
    }


    companion object {
        const val SYMBOL_0 = 0
        const val SYMBOL_1 = 1
        const val SYMBOL_2 = 2
        const val SYMBOL_3 = 3
        const val SYMBOL_4 = 4

        const val CARD_ME = 6
        const val CARD_YOU = 7

    }
}