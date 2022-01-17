package org.cardna.ui.maincard

import android.os.Bundle
import android.view.View
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityDetailCardMeBinding

class DetailCardMeActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailCardMeBinding>(R.layout.activity_detail_card_me) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        getMainCardId()
        enableCheckLike()
    }

    private fun getMainCardId() {
        //메인카드에서 선택된 타드의 id값 얻어옴
        val id = intent.getIntExtra("id", 0)
        binding.tvDetailcardTitle.text = id.toString()

        //얻어온 id값을 이용해 서버에서 상세카드 조회
        initNetwork(id)
        setCardYou()
    }

    //상세카드 조회->ResponseDetailData 데이터 값 받아옴
    private fun initNetwork(id: Int) {}

    //카드너/나 일때에 따른 레이아웃 분기
    private fun setCardYou() {
        //카드나 일때
        if (binding.tvDetailcardTitle.text == "1") {
            //관계 text 안보이게함
            binding.tvDetailcardUserName.visibility = View.GONE
            //쓰레기통 아이콘 띄게함

        } else {
            with(binding) {
                //배경색 보라색으로
                clDetailcardSettingLayout.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)
                //관계 text 보이게함
                tvDetailcardUserName.visibility = View.VISIBLE
                //쓰레기통 아이콘,배경 교체
                ibtnDetailcardDelete.setImageResource(R.drawable.ic_detail_3dot)
                ibtnDetailcardDelete.setBackgroundResource(R.drawable.rectangle_main_purple)
            }
        }
    }

    // 공감버튼 체크 되도록
    private fun enableCheckLike(){
        binding.ctvLikeIcon.setOnClickListener{
            binding.ctvLikeIcon.toggle()
        }
    }
}