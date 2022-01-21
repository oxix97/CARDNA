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
import org.cardna.data.remote.model.mypage.RequestFriendUpdateData
import org.cardna.data.remote.model.mypage.ResponseFriendSearchEmailData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.ActivitySearchEmailBinding
import org.cardna.util.initRootClickEvent
import org.cardna.util.shortToast

class SearchEmailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySearchEmailBinding>(R.layout.activity_search_email) {


    var friendstate = false
    var friendId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        transactionEvent()
        initRootClickEvent(binding.ctlSearchemailTop)
        switchFriend()
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
                            friendId = ApiService.friendService.getFriendSearchEmail(friendEmail).data.id  //친구 id가져오기

                            if (result.success) {
                                //친구 검색 성공시 아이템 세팅해서 띄우기
                                clSearchemail.visibility = View.VISIBLE
                                binding.clSearchemail.visibility = View.VISIBLE
                                withContext(Dispatchers.Main) {
                                    Glide.with(this@SearchEmailActivity).load(result.data.userImg).circleCrop().into(ivSearchemailFriendProfile)
                                    tvSearchemailFriendName.text = result.data.name
                                    tvSearchemailFriendEmail.text = result.data.email

                                    //친구추가 버튼은 친구인지 상태따라 분기
                                    friendstate = result.data.isFriend
                                    Log.d("이미친구인가", friendstate.toString())
                                    showFriendIntState()

                                    /*  if (result.data.isFriend) {
                                          //이미 친구인 경우
                                      }*/
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

    //이미 친구인지에 따른 버튼 분기
    private fun showFriendIntState() {
        Log.d("친구여부따른 버튼 분기",friendstate.toString())
        //이미 친구인 경우
        if (friendstate) { //친구 추가체크  친구인경우
            binding.tvSearchemailCheckedAddFriend.visibility = View.VISIBLE
            binding.ivSearchemailCheckImage.visibility = View.VISIBLE
            binding.tvSearchemailAddFriend.visibility = View.GONE

            //친구가 아닌경우
        } else {  //친구 추가 해제
            binding.tvSearchemailAddFriend.visibility = View.VISIBLE
            binding.tvSearchemailCheckedAddFriend.visibility = View.GONE
            binding.ivSearchemailCheckImage.visibility = View.GONE

        }
        //    binding.clSearchemail.visibility = View.VISIBLE
        /*   binding.ivSearchemailAddFriend.setOnClickListener {
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
           }*/
    }

    fun switchFriend() {
        binding.ivSearchemailAddFriend.setOnClickListener {
           val swith= !friendstate
            Log.d("친구추가버튼눌럿으니까 상태 바뀜",swith.toString())
            binding.ivSearchemailAddFriend.isSelected = !binding.ivSearchemailAddFriend.isSelected
            if (swith) {  //친구 추가체크  친구인경우
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
        lifecycleScope.launch {
            try {
                val result = ApiService.friendService.postFriend(RequestFriendUpdateData(friendId)).data
                Log.d("성공", result.isFriend.toString())
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
                Log.d("실패", "친구추가 실패")
            }
        }
    }
}
