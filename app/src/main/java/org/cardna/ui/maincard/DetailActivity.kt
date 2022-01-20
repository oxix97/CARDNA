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
import org.cardna.ui.cardpack.CardCreateCompleteActivity
import org.cardna.util.shortToast

class DetailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }



    override fun initView() {
        initNetwork()
    }

    private fun initNetwork() {
        // OtherWriteActivity에서 넘겨진 userId값에 해당하는 카드 상세 정보를 서버에서 받아와서 뿌려줌
        lifecycleScope.launch {
            val id = intent.getIntExtra("id", 0)
            val dataContainer =
                ApiService.cardService.getCardDetail(id) // 카드 상세 조회

            // 받아온 데이터를 뷰에 뿌려줌
            setCardDetail(dataContainer.data, dataContainer.message)
        }
    }


    // 받아온 데이터 중 쓸 것
    // isMe -> 카드나인지, 너인지
    // title -> 카드 제목
    // cardImg -> img url 이므로
    // content
    // relation
    // name - 작성자 이름
    // creatdeAt - 만들어진 날짜


    // 받아온 데이터 중 다음 액티비티로 넘겨줄 것

    // isMe -> 카드나인지, 너인지
    // title -> 카드 제목
    // cardImg -> img url 이므로


    // 받아온 데이터를 뷰에 뿌려줌
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
                createCardYou(cardInfo.id.toInt(), cardInfo.isMe, cardInfo.cardImg, cardInfo.title, message)
                //   createCardYou(cardInfo.id.toInt(), message)
            }
        }
    }


    //카드너 만들기 put
    private fun createCardYou(id: Int, isMe: Boolean, cardImg: String, title: String,  message: String) {
        lifecycleScope.launch {
            ApiService.cardService.putCardBoxCardId(id)
        }

        // 다음 인텐트로 넘어가서 (정보를 넘겨주면서) CardCreateCompleteActivity 띄워줌.
        val intent = Intent(this, CardCreateCompleteActivity::class.java)
        intent.putExtra("meOrYou", CARD_YOU)
        intent.putExtra("cardTitle", title)
        intent.putExtra("cardImg", cardImg)
        startActivity(intent)
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