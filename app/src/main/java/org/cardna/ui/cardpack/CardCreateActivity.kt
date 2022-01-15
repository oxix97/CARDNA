package org.cardna.ui.cardpack

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateBinding

class CardCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateBinding>(R.layout.activity_card_create) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setChooseCardListener()
        makeCardListener()
    }

    override fun initView() {

    }

    private fun setChooseCardListener() {
        binding.clCardcreateImg.setOnClickListener {
            val bottomDialogImageFragment: BottomDialogImageFragment = BottomDialogImageFragment {
                when (it) {
                    SYMBOL_0 -> {
                        Toast.makeText(this, "SYMBOL_0", Toast.LENGTH_SHORT).show()
                        // SYMBOL_0에 해당하는 이미지로 카드의 이미지 설정
                    }
                    SYMBOL_1 -> {
                        Toast.makeText(this, "SYMBOL_1", Toast.LENGTH_SHORT).show()
                    }
                    SYMBOL_2 -> {
                        Toast.makeText(this, "SYMBOL_2", Toast.LENGTH_SHORT).show()
                    }
                    SYMBOL_3 -> {
                        Toast.makeText(this, "SYMBOL_3", Toast.LENGTH_SHORT).show()
                    }
                    SYMBOL_4 -> {
                        Toast.makeText(this, "SYMBOL_4", Toast.LENGTH_SHORT).show()
                    }
                    GALLERY -> {
                        Toast.makeText(this, "GALLERY", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            bottomDialogImageFragment.show(supportFragmentManager, bottomDialogImageFragment.tag)
        }
    }

    private fun makeCardListener() {
        binding.btnCardcreateComplete.setOnClickListener {
            // 카드나 만들기 버튼을 눌렀을 때, dialog를 띄워준다.
            val meOrYou = CARD_ME
            val cardImg = R.drawable.ic_symbol_cardme_0 // 나중에 바꿔야 함
            val cardTitle = binding.etCardcreateKeyword.text.toString()

            val dialog = CardCreateCompleteDialog(this, meOrYou, cardImg, cardTitle)
            dialog.showDialog()
        }
    }


    companion object {
        const val SYMBOL_0 = 0
        const val SYMBOL_1 = 1
        const val SYMBOL_2 = 2
        const val SYMBOL_3 = 3
        const val SYMBOL_4 = 4
        const val GALLERY = 5

        const val CARD_ME = 6
        const val CARD_YOU = 7
    }
}