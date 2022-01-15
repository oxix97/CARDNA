package org.cardna.ui.mypage

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.cardna.R

class OtherCardCreateCompleteDialog(
    context: Context
) {
    private val dialog = Dialog(context)

    fun showDialog() {
        dialog.setContentView(R.layout.item_othercardcreate_complete_dialog)

        // 정확히 가운데에 뜨는 건 아닌 것 같음, 수정 필요
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )


        // dialog.setCanceledOnTouchOutside(true)
        // dialog가 취소가 가능하도록 dialog.setCancelable(true)

        dialog.show()
    }

    companion object {
        const val CARD_ME = 6
        const val CARD_YOU = 7
    }
}