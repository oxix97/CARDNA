package org.cardna.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.mypage.ResponseFriendSearchEmailData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ActivitySearchEmailBinding
import org.cardna.util.shortToast

class SearchEmailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySearchEmailBinding>(R.layout.activity_search_email) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        transactionEvent()
    }

    private fun transactionEvent() {
        with(binding) {
            clSearchemail.visibility = View.INVISIBLE
            var friendEmail = ""
            etSearchemailBackground.setOnEditorActionListener { textView, action, event ->
                var handled = false
                if (action == EditorInfo.IME_ACTION_DONE) {
                    //네트워크 통신 성공하면 text값 가져와서 일치하는 item 프로필, 이름, 이메일 띄우기
                    friendEmail = etSearchemailBackground.text.toString()
                    var result: ResponseFriendSearchEmailData
                    lifecycleScope.launch {
                        try {
                            result = ApiService.friendService.getFriendSearchEmail(friendEmail)
                            if (result.success) {
                                clSearchemail.visibility = View.VISIBLE
                                binding.clSearchemail.visibility = View.VISIBLE
                                withContext(Dispatchers.Main) {
                                    Glide.with(this@SearchEmailActivity).load(result.data.userImg).circleCrop().into(ivSearchemailFriendProfile)
                                    tvSearchemailFriendName.text = result.data.name
                                    tvSearchemailFriendEmail.text = result.data.email
                                    if (result.data.isFriend) {
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            shortToast("존재하지 않는 회원입니다.")
                        }
                    }

                    //   showFriend(cardImg, title, sentence)
                    handled = true
                }
                handled
            }

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