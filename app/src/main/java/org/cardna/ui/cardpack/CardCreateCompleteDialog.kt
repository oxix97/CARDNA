package org.cardna.ui.cardpack

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import org.cardna.R
import org.cardna.util.LinearGradientSpan

class CardCreateCompleteDialog(
    val context: Context,
    private val meOrYou: Int,
    private val cardImg: Int,
    private val cardTitle: String
) {
    private val dialog = Dialog(context)

    fun showDialog() {
        dialog.setContentView(R.layout.item_cardcreate_complete_dialog)

        // 정확히 가운데에 뜨는 건 아닌 것 같음, 수정 필요
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )


        // dialog.setCanceledOnTouchOutside(true)
        // dialog가 취소가 가능하도록 dialog.setCancelable(true)

        // 카드나인지 너 인지에 따라 text 바꿔주기
        // constraintLayout 배경 바꿔주기
        if (meOrYou == CARD_ME){
            dialog.findViewById<TextView>(R.id.tv_cardcreate_complete).text = "카드나를 만들었어요!"
            dialog.findViewById<ConstraintLayout>(R.id.cl_cardcreate_complete).setBackgroundResource(R.drawable.background_cardme)
        }
        else{
            dialog.findViewById<TextView>(R.id.tv_cardcreate_complete).text = "카드너를 만들었어요!"
            dialog.findViewById<ConstraintLayout>(R.id.cl_cardcreate_complete).setBackgroundResource(R.drawable.background_cardyou)
        }


        // cardImg에 해당하는 이미지 바꿔주기
        dialog.findViewById<ImageView>(R.id.iv_cardcreate_complete).setImageResource(cardImg)

        // cardTitle에 해당하는 title로 바꿔주기
        dialog.findViewById<TextView>(R.id.tv_cardcreate_complete_title).text = cardTitle

        // textColor 바꾸기
        setTextGradient()

        // 3초간 보여주고 그 다음 액티비티 POP 해야 함
        dialog.show()
    }


    fun setTextGradient() {
        val text = dialog.findViewById<TextView>(R.id.tv_cardcreate_complete).text.toString()
        val green = ContextCompat.getColor(context, R.color.main_green)
        val purple = ContextCompat.getColor(context, R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        dialog.findViewById<TextView>(R.id.tv_cardcreate_complete).text = spannable
    }

    companion object {
        const val CARD_ME = 6
        const val CARD_YOU = 7
    }
}