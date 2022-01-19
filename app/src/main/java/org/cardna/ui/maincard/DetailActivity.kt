package org.cardna.ui.maincard

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
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
            val dataContainer =
                ApiService.cardService.getCardDetail(id)

            setCardDetail(dataContainer.data, dataContainer.message)
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
}