package org.cardna.ui.maincard

import android.animation.Animator
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.cardpack.ResponseCardDetailData
import org.cardna.data.remote.model.detail.RequestLikeData
import org.cardna.databinding.ActivityDetailCardMeBinding
import org.cardna.util.shortToast

class DetailCardMeActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailCardMeBinding>(R.layout.activity_detail_card_me) {

    private lateinit var DetailCardData: ResponseCardDetailData.Data
    private var isChecked = false
    private var type: String = ""
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

        lifecycleScope.launch {
            try {
                val isLike = ApiService.likeService.postLike(RequestLikeData(id)).data.isLiked
                Log.d("isLiked", isLike.toString())
                initNetwork(id, isMyCard, isLike)
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    //상세카드 조회->ResponseDetailData 데이터 값 받아옴
    private fun initNetwork(id: Int, isMyCard: Boolean, isLike: Boolean) {
        //isLike -> true 면 이미 눌려 있는거
        if (isLike) {
            binding.ctvLikeIcon.isChecked = true
            isChecked = isLike
        }
        lifecycleScope.launch {
            try {
                DetailCardData = ApiService.cardService.getCardDetail(id).data

                if (isMyCard == true && DetailCardData.isMe == true) {  //내가 카드나 상세
                    setUserCardMe(id)
                } else if (isMyCard == true && DetailCardData.isMe == false) {    //내가 카드너 상세
                    setUserCardYou(id)
                    //다이어로그
                } else if (isMyCard == false && DetailCardData.isMe == true) {     //타인이 카드나 상세
                    setOtherCardMe()
                    showLikeLottie("setOtherCardMe")
                } else if (isMyCard == false && DetailCardData.isMe == false) {          //타인이 카드너 상세
                    setOtherCardYou()
                    showLikeLottie("setOtherCardYou")
                }
                setData(isLike, id)
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    //내가 카드나 상세
    private fun setUserCardMe(id: Int) {
        with(binding) {
            //공감 아이콘 없애기
            ctvLikeIcon.visibility = View.INVISIBLE
            //관계 text없애기
            tvDetailcardUserName.visibility = View.GONE

            //삭제버튼 클릭시 네트워크 통신
            ibtnDetailcardDelete.setOnClickListener {
                showUserCardMeDialog(id)  //삭제
            }
        }
    }

    //내가 카드너 상세
    private fun setUserCardYou(id: Int) {
        with(binding) {
            //공감 아이콘 없애기
            ctvLikeIcon.visibility = View.INVISIBLE

            //배경색 보라색으로
            clDetailcardSettingLayout.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)

            //관계 text 보이기
            tvDetailcardUserName.visibility = View.VISIBLE

            //쓰레기통->3dot 아이콘, 보관, 삭제 다이어로그 띄우기
            ibtnDetailcardDelete.setImageResource(R.drawable.ic_detail_3dot)
            ibtnDetailcardDelete.setOnClickListener {
                showUserCardYouDialog(id)  //보관, 삭제
            }
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

    private fun setData(isLike: Boolean, id: Int) {
        with(binding) {
            Glide.with(this@DetailCardMeActivity).load(DetailCardData.cardImg)
                .into(binding.ivDetailcardImage)

            //타이틀
            tvDetailcardTitle.text = DetailCardData.title

            //content
            tvDetailcardAbout.text = DetailCardData.content

            //createdAt
            tvDetailcardDate.text = DetailCardData.createdAt

            //관계
            tvDetailcardUserName.text = DetailCardData.relation
        }
    }

    //로티 띄우기
    private fun showLikeLottie(type: String) {
        binding.ctvLikeIcon.setOnClickListener {

            //false->true일때만 로티 띄우기
            binding.ctvLikeIcon.toggle()
            if (binding.ctvLikeIcon.isChecked) {
                val lottie = findViewById<LottieAnimationView>(R.id.lottie)

                if (type == "setOtherCardMe") {
                    lottie.setAnimation("lottie_cardme.json")
                } else {
                    lottie.setAnimation("lottie_cardyou.json")
                }
                binding.lottie.setVisibility(View.VISIBLE)
                lottie.loop(false)
                lottie.playAnimation()
                lottie.setRepeatCount(1);

                lottie.addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                        lottie.setVisibility(View.VISIBLE)
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        lottie.setVisibility(View.GONE)
                    }

                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                })
                lottie.setVisibility(View.GONE)
            }
        }
    }

    private fun showUserCardMeDialog(id: Int) {
        val dialog = Dialog(this)     // Dialog 초기화
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀 제거
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.Transparent.toArgb()))  //둥근테두리로 만들려고 background를 투명하게
        dialog.setContentView(R.layout.dialog_detail_cardme)             // xml 레이아웃 파일과 연결(다이어로그 레이아웃)
        dialog.setCancelable(true)  // 다이얼로그외에 다른 화면을 눌렀을 때 나가는 것 방지

        dialog.getWindow()!!.setGravity(Gravity.BOTTOM)


        dialog.show()// 다이얼로그 띄우기

        // 진짜 삭제
        val yesBtn = dialog.findViewById<Button>(R.id.tv_dialog_yes)
        yesBtn.setOnClickListener {
            //삭제 서버 통신
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

        // 삭제 안함
        val noBtn = dialog.findViewById<Button>(R.id.tv_dialog_no)
        noBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showUserCardYouDialog(id: Int) {
        val dialog = Dialog(this)     // Dialog 초기화
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀 제거
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.Transparent.toArgb()))  //둥근테두리로 만들려고 background를 투명하게
        dialog.setContentView(R.layout.dialog_detail_cardyou)             // xml 레이아웃 파일과 연결(다이어로그 레이아웃)
        dialog.setCancelable(true)  // 다이얼로그외에 다른 화면을 눌렀을 때 나가는 것 방지

        dialog.getWindow()!!.setGravity(Gravity.BOTTOM)

        dialog.show()// 다이얼로그 띄우기

        // 보관 버튼
        val noBtn = dialog.findViewById<Button>(R.id.tv_dialog_save)
        noBtn.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val dataContainer = ApiService.cardService.putCardBoxCardId(id)
                } catch (e: Exception) {
                    Log.d("실패", e.message.toString())
                }
            }
            dialog.dismiss() //토스트 띄우고 다이어로그 사라지게
            shortToast("보관되었습니다.")
            finish()
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
            var intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    //공감버튼 서버 연결
    private fun cardLike() {
        val id = intent.getIntExtra("id", 0)
        val data = RequestLikeData(id)
        lifecycleScope.launch {
            try {
                ApiService.likeService.postLike(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //뷰가 destroy될때 서버에 공감 눌럿는지 안눌럿는지 통신함
        cardLike()
    }

    companion object {
        const val SPLASH_VIEW_TIME = 670L
    }
}