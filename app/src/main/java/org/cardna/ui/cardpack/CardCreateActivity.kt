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
        setListener()
        setChooseCardListener()
    }

    override fun initView() {

    }

    private fun setListener() {
        binding.btnCardcreateComplete.setOnClickListener {
            val intent = Intent(this, CardCreateCompleteActivity::class.java)
            startActivity(intent)
        }
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

    companion object {
        const val SYMBOL_0 = 0
        const val SYMBOL_1 = 1
        const val SYMBOL_2 = 2
        const val SYMBOL_3 = 3
        const val SYMBOL_4 = 4
        const val GALLERY = 5
    }
}