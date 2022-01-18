package org.cardna.ui.maincard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.cardpack.ResponseCardDetailData
import org.cardna.databinding.ActivityDetailCardMeBinding

class DetailCardMeActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailCardMeBinding>(R.layout.activity_detail_card_me) {

    private lateinit var DetailCardData: ResponseCardDetailData.Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        getCardData()
    }

    private fun getCardData() {
        val id = intent.getIntExtra("id", 0)
        val isMyCard = intent.getBooleanExtra("isMyCard", false)
        initNetwork(id, isMyCard)
    }

    //상세카드 조회->ResponseDetailData 데이터 값 받아옴
    private fun initNetwork(id: Int, isMyCard: Boolean) {
        lifecycleScope.launch {
            try {
                DetailCardData = ApiService.cardService.getCardDetail(id).data

                if (isMyCard == true && DetailCardData.isMe == true) {  //내가 카드나 상세
                    setUserCardMe()
                } else if (isMyCard == true && DetailCardData.isMe == false) {    //내가 카드너 상세
                    setUserCardYou()
                } else if (isMyCard == false && DetailCardData.isMe == true) {     //타인이 카드나 상세
                    setOtherCardMe()
                } else if (isMyCard == false && DetailCardData.isMe == false) {          //타인이 카드너 상세
                    setOtherCardYou()
                }
                setData()
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    //내가 카드나 상세
    private fun setUserCardMe() {
        with(binding) {
            //공감 아이콘 없애기
            ctvLikeIcon.visibility = View.INVISIBLE
            //관계 text없애기
            tvDetailcardUserName.visibility = View.GONE
        }
    }

    //내가 카드너 상세
    private fun setUserCardYou() {
        with(binding) {

            //공감 아이콘 없애기
            ctvLikeIcon.visibility = View.INVISIBLE

            //배경색 보라색으로
            clDetailcardSettingLayout.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)

            //관계 text 보이기
            tvDetailcardUserName.visibility = View.VISIBLE

            //쓰레기통->3dot 아이콘
            ibtnDetailcardDelete.setImageResource(R.drawable.ic_detail_3dot)

            //배경 보라색으로
            ibtnDetailcardDelete.setBackgroundResource(R.drawable.rectangle_main_purple)
        }
    }

    //타인이 카드나 상세
    private fun setOtherCardMe() {
        with(binding) {
            //화살표 없애기
            ibtnShare.visibility = View.INVISIBLE

            //쓰레기통 없애기
            ibtnDetailcardDelete.visibility = View.INVISIBLE

            //관계 text없애기
            tvDetailcardUserName.visibility = View.GONE
        }
    }

    //타인이 카드너 상세
    private fun setOtherCardYou() {
        //배경 보라색으로 바꾸기
        with(binding) {
            //배경색 보라색으로
            ibtnDetailcardDelete.setBackgroundResource(R.drawable.rectangle_main_purple)
            clDetailcardSettingLayout.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)

            //화살표 없애기
            ibtnShare.visibility = View.INVISIBLE

            //쓰레기통 없애기
            ibtnDetailcardDelete.visibility = View.INVISIBLE

        }
    }

    private fun setData() {
        with(binding) {
            Glide.with(this@DetailCardMeActivity).load(DetailCardData.cardImg).into(binding.ivDetailcardImage)

            //타이틀
            tvDetailcardTitle.text = DetailCardData.title

            //content
            tvDetailcardAbout.text = DetailCardData.content

            //createdAt
            tvDetailcardDate.text = DetailCardData.createdAt

            //관계
            tvDetailcardUserName.text = DetailCardData.relation

            ctvLikeIcon.setOnClickListener {
                ctvLikeIcon.toggle()
            }
        }
    }
}