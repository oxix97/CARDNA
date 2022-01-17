package org.cardna.ui.cardpack

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.viewpager2.widget.ViewPager2
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateCompleteBinding
import org.cardna.util.LinearGradientSpan

class CardCreateCompleteActivity :
BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateCompleteBinding>(R.layout.activity_card_create_complete) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setCardMeOrYou()
        setLottie()
    }


    private fun setCardMeOrYou(){
        val meOrYou = intent.getIntExtra("meOrYou", 0)
        val cardImg = intent.getIntExtra("cardImg", 0)
        val cardTitle = intent.getStringExtra("cardTitle")

        // 카드나인지, 카드너인지에 따라 뷰 띄워주기
        // textView 바꿔주기
        if(meOrYou == CARD_ME){
            binding.tvCardcreateComplete.text = "카드나를 만들었어요!"
            binding.clCardcreateComplete.setBackgroundResource(R.drawable.background_cardme)
        }
        else if(meOrYou == CARD_YOU){
            binding.tvCardcreateComplete.text = "카드너를 만들었어요!"
            binding.clCardcreateComplete.setBackgroundResource(R.drawable.background_cardyou)
        }
        else{

        }

        binding.ivCardcreateComplete.setImageResource(cardImg)
        binding.tvCardcreateCompleteTitle.text = cardTitle
        setTextGradient()
    }

    fun setTextGradient() {
        val text = binding.tvCardcreateComplete.text.toString()
        val green = getColor(R.color.main_green)
        val purple = getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.tvCardcreateComplete.text = spannable
    }


    // 로티 띄워주기
    private fun setLottie(){
        val handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            // MainActivity로 갈 때, CardCreateActivity pop하고 가기
            // 현재 A -> B -> C인데, C -> A로 가도록 intent 써서
        }, LOTTIE_VIEW_TIME) // 이는 CardCreateActivity가 얼마나 띄워주고 다시 main으로 갈 건지에 대한 시간, 로티가 뜨느 시간은 아님
    }

    companion object {
        const val CARD_ME = 6
        const val CARD_YOU = 7

        const val LOTTIE_VIEW_TIME = 1670L
    }

}