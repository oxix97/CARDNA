package org.cardna.ui.cardpack

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.bumptech.glide.Glide
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateCompleteBinding
import org.cardna.ui.maincard.DetailActivity
import org.cardna.ui.mypage.OtherWriteActivity
import org.cardna.util.LinearGradientSpan

class CardCreateCompleteActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateCompleteBinding>(R.layout.activity_card_create_complete) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setCardMeOrYou()
    }


    private fun setCardMeOrYou() {
        val meOrYou = intent.getIntExtra("meOrYou", 0)
        val cardImg = Uri.parse(intent.getStringExtra("cardImg")) // uri를 string으로 변환한 값을 받아 다시 uri로
        val cardTitle = intent.getStringExtra("cardTitle")
        val symbolId = intent.getIntExtra("symbolId", -1) // symbolId가 null일 때 -1로

        // 카드나인지, 카드너인지에 따라 뷰 띄워주기
        // textView 바꿔주기
        if (meOrYou == CARD_ME) { // 카드나 작성 완료 하면 만들기
            binding.tvCardcreateComplete.text = "카드나를 만들었어요!"
            binding.clCardcreateComplete.setBackgroundResource(R.drawable.background_cardme)

            if (symbolId == -1) { // 이미지를 띄워주면 됨.
                Glide.with(this).load(cardImg).into(binding.ivCardcreateComplete)
                // Toast.makeText(this, "null", Toast.LENGTH_SHORT)
            } else { // symbolId가 null이 아니라면 이에 해당하는 각 심볼 이미지 띄워주면 됨 이라면,
                when (symbolId) {
                    SYMBOL_0 -> binding.ivCardcreateComplete.setImageResource(R.drawable.ic_symbol_cardme_0)
                    SYMBOL_1 -> binding.ivCardcreateComplete.setImageResource(R.drawable.ic_symbol_cardme_1)
                    SYMBOL_2 -> binding.ivCardcreateComplete.setImageResource(R.drawable.ic_symbol_cardme_2)
                    SYMBOL_3 -> binding.ivCardcreateComplete.setImageResource(R.drawable.ic_symbol_cardme_3)
                    SYMBOL_4 -> binding.ivCardcreateComplete.setImageResource(R.drawable.ic_symbol_cardme_4)
                }
            }
        } else if (meOrYou == CARD_YOU) { // 카드너 추가 완료 만들기
            binding.tvCardcreateComplete.text = "카드너를 만들었어요!"
            binding.clCardcreateComplete.setBackgroundResource(R.drawable.background_cardyou)

            /* 카드너일 경우, symbol로 된 이미지도 uri로 줄것이므로 glide이용해서 uri를 imageView에 띄워주기만 하면 될 듯 */
            Glide.with(this).load(cardImg).into(binding.ivCardcreateComplete)
        }

        binding.tvCardcreateCompleteTitle.text = cardTitle
        setTextGradient()


        // 로티 띄워주고 인텐트 이용해서 이전 액티비티로 가기
        val handler = Handler()
        if (meOrYou == CARD_ME) { // 카드나일 경우,
            handler.postDelayed({
                // 카드나 작성에서 왔다면 MainActivity로 돌아가도록 intent를 연결시켜줘야 하고,
                var intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                // MainActivity로 갈 때, CardCreateActivity pop하고 가기
                // 현재 A -> B -> C인데, C -> A로 가도록 intent 써서
            }, LOTTIE_VIEW_TIME) // 이는 CardCreateActivity가 얼마나 띄워주고 다시 main으로 갈 건지에 대한 시간, 로티가 뜨느 시간은 아님
        } else if(meOrYou == CARD_YOU) {
            handler.postDelayed(
                {
                    // 카드너 추가에서 왔다면 OtherWriteActivity로 돌아가야 한다. 근데 이때 OtherWriteAcitivty로 전달해줄 정보는 없고
                    // OtherWriteActivity에서 서버 통신 다시 하도록
                    var intent = Intent(this, OtherWriteActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    // MainActivity로 갈 때, CardCreateActivity pop하고 가기
                    // 현재 A -> B -> C인데, C -> A로 가도록 intent 써서
                },
                LOTTIE_VIEW_TIME
            )
        }
    }

    fun setTextGradient() {
        val text = binding.tvCardcreateComplete.text.toString()
        val green = getColor(R.color.main_green)
        val purple = getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.tvCardcreateComplete.text = spannable
    }



    companion object {
        const val SYMBOL_0 = 0
        const val SYMBOL_1 = 1
        const val SYMBOL_2 = 2
        const val SYMBOL_3 = 3
        const val SYMBOL_4 = 4

        const val CARD_ME = 6
        const val CARD_YOU = 7

        const val LOTTIE_VIEW_TIME = 1670L
    }
}