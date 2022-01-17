package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityOtherCardWriteBinding

class OtherCardWriteActivity
    : BaseViewUtil.BaseAppCompatActivity<ActivityOtherCardWriteBinding>(R.layout.activity_other_card_write) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setListener()
    }


    private fun setListener(){
     binding.btnOthercardwriteComplete.setOnClickListener{
         // 이전 액티비티로 부터 relation 받아서, 이미지, 제목, 내용 다 같이 서버로 전달
         val relation = intent.getStringExtra("relation")


         // 두개의 editText가 다 입력되었을 때, 이미지도 선택했을 때 버튼이 클릭될 수 있도록
         intent = Intent(this, OtherCardCreateCompleteActivity::class.java)
         startActivity(intent)
     }
    }
    // OtherCardCreateActivity에서 넘어오면 바텀씻 띄워서 이미지까지 받고, 작성완료 하면
    //
    // 타인과의 관계, 이미지, 제목, 내용 정보 가지고 데이터 어디에 전달 ?


    // CardCreateActivity랑 비슷하게 코드 짜기
}