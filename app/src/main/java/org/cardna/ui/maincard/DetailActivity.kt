package org.cardna.ui.maincard

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.cardpack.ResponseCardDetailData
import org.cardna.databinding.ActivityDetailBinding
import org.cardna.util.shortToast

class DetailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initNetwork()
        getCardPackData()
    }

    private fun getCardPackData() {
        val cardImg = intent.getIntExtra("cardImg", 0)
        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")
        val isImage = intent.getStringExtra("isImage")

        with(binding) {
            ivDetailcardImage.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)
            tvDetailcardUserName.text = id.toString()
            tvDetailcardAbout.text = title
        }
    }

    private fun initNetwork() {
        lifecycleScope.launch {
            val id = intent.getIntExtra("id", 0)
            val dataContainer = ApiService.cardService.getCardDetail(id)
            setCardDetail(dataContainer.data, dataContainer.message)
            showUserCardDialog(id)
        }
    }

    //카드너 만들기 put
    private fun createCardYou(id: Int, message: String) {
        lifecycleScope.launch {
            ApiService.cardService.putCardBoxCardId(id)
        }
        shortToast(message)
        finish()
    }

    private fun setCardDetail(cardInfo: ResponseCardDetailData.Data, message: String) {
        binding.apply {
            tvDetailcardTitle.text = cardInfo.title
            tvDetailcardAbout.text = cardInfo.content
            tvDetailcardUserName.text = cardInfo.relation
            tvDetailcardDate.text = cardInfo.createdAt
            tvDetailcardUserName.text = cardInfo.name
            Glide
                .with(this@DetailActivity)
                .load(cardInfo.cardImg)
                .fitCenter()
                .into(ivDetailcardImage)
            btnDetailCardCreate.setOnClickListener {
                createCardYou(cardInfo.id.toInt(), message)
            }
        }
    }

    private fun showUserCardDialog(id: Int) {
        binding.ibtnShare.setOnClickListener {
            val dialog = Dialog(this)     // Dialog 초기화
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀 제거
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.Transparent.toArgb()))  //둥근테두리로 만들려고 background를 투명하게
            dialog.setContentView(R.layout.dialog_detail_cardyou)             // xml 레이아웃 파일과 연결(다이어로그 레이아웃)
            dialog.setCancelable(true)  // 다이얼로그외에 다른 화면을 눌렀을 때 나가는 것 방지

            dialog.getWindow()!!.setGravity(Gravity.BOTTOM)

            dialog.show()// 다이얼로그 띄우기

            // 신고 버튼
            val noBtn = dialog.findViewById<Button>(R.id.tv_dialog_save)
            dialog.findViewById<Button>(R.id.tv_dialog_save).text = "신고"
            noBtn.setOnClickListener {
                shortToast("준비중입니다.")
            }


            // 삭제 버튼
            val deleteBtn = dialog.findViewById<Button>(R.id.tv_dialog_delete)
            deleteBtn.setOnClickListener {
                lifecycleScope.launch {
                    try {
                        val dataContainer = ApiService.cardService.deleteCard(id)
                    } catch (e: Exception) {
                        Log.d("실패", e.message.toString())
                    }
                }
                dialog.dismiss()
                shortToast("삭제되었습니다.")
                finish()
            }
        }
    }
}