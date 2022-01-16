package org.cardna.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.text.set
import androidx.core.text.toSpannable
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateCompleteBinding
import org.cardna.databinding.ActivityOtherCardCreateCompleteBinding
import org.cardna.util.LinearGradientSpan

class OtherCardCreateCompleteActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardCreateCompleteBinding>(R.layout.activity_other_card_create_complete) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun initView() {
        setTextGradient()
        setLottie()
    }

    fun setTextGradient() {
        val text = binding.tvOthercardcreateComplete.toString()
        val green = getColor(R.color.main_green)
        val purple = getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.tvOthercardcreateComplete.text = spannable
    }


    // 로티 띄워주기
    private fun setLottie(){
        val handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // 진입점이 두개인데 어차피 둘다 MainActivity이므로
            // MainActivity로 갈 때, CardCreateActivity pop하고 가기
            // 현재 A -> B -> C인데, C -> A로 가도록 intent 써서
        }, LOTTIE_VIEW_TIME) // 이는 CardCreateActivity가 얼마나 띄워주고 다시 main으로 갈 건지에 대한 시간, 로티가 뜨느 시간은 아님
    }

    companion object {
        const val LOTTIE_VIEW_TIME = 1670L
    }

}