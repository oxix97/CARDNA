package org.cardna.ui.mypage

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySearchEmailBinding
import org.cardna.util.shortToast

class SearchEmailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySearchEmailBinding>(R.layout.activity_search_email) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transactionEvent()
    }

    override fun initView() {
    }

    private fun transactionEvent() {
        binding.clSearchemail.visibility = View.INVISIBLE
        binding.etSearchemailBackground.setOnEditorActionListener { textView, action, event ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                //네트워크 통신 성공하면 text값 가져와서 일치하는 item 프로필, 이름, 이메일 띄우기

             //   showFriend(cardImg, title, sentence)
                handled = true
            }
            handled
        }

    }

    private fun showFriend(cardImg: String, title: String, sentence: String) {
        binding.clSearchemail.visibility = View.VISIBLE
        binding.ivSearchemailAddFriend.setOnClickListener {
            binding.ivSearchemailAddFriend.isSelected = !binding.ivSearchemailAddFriend.isSelected
            if (binding.ivSearchemailAddFriend.isSelected) {  //친구 추가체크
                binding.tvSearchemailCheckedAddFriend.visibility = View.VISIBLE
                binding.ivSearchemailCheckImage.visibility = View.VISIBLE
                binding.tvSearchemailAddFriend.visibility = View.GONE
            } else {  //친구 추가 해제
                binding.tvSearchemailAddFriend.visibility = View.VISIBLE
                binding.tvSearchemailCheckedAddFriend.visibility = View.GONE
                binding.ivSearchemailCheckImage.visibility = View.GONE
            }
            friendUpdate()
        }
    }

    private fun friendUpdate() {
        //친구 추가인지 아닌지 업데이트하기
    }
}