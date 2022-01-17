package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.login.RequestSignUpEmailData
import org.cardna.data.remote.model.login.ResponseSignUpEmailData
import org.cardna.databinding.ActivitySignUpNameBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpNameActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpNameBinding>(R.layout.activity_sign_up_name) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        nameTextChanged()
        initNetwork()
    }

    private fun nameTextChanged() {
        binding.etSignUpName.addTextChangedListener {
            if (binding.etSignUpName.length() > 0) {
                binding.clEditName.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
            } else {
                binding.clEditName.setBackgroundResource(R.drawable.bottom_edit_line_white_4)
            }
            setButtonBackground()
        }
    }

    private fun setButtonBackground() {
        if (binding.etSignUpName.length() > 0) {
            binding.btnSignUpNameAccess.setBackgroundResource(R.drawable.rectangle_sign_in_gradient_radius_10)
            binding.btnSignUpNameAccess.setTextColor(getColor(R.color.black))
        } else {
            binding.btnSignUpNameAccess.setBackgroundResource(R.drawable.rectangle_dark_grey_radius_10)
            binding.btnSignUpNameAccess.setTextColor(getColor(R.color.white_3))
        }
    }

    private fun onClickSignInActivity() {
        binding.btnSignUpNameAccess.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initNetwork() {
        val getEmail = intent.getStringExtra("email").toString()
        val getPassword = intent.getStringExtra("password").toString()
        val getName = binding.etSignUpName.text.toString()
        val requestLoginData = RequestSignUpEmailData(
            email = getEmail,
            password = getPassword,
            name = getName,
        )
        val call: Call<ResponseSignUpEmailData> =
            ApiService.authService.postSignUp(requestLoginData)
        call.enqueue(object : Callback<ResponseSignUpEmailData> {
            override fun onResponse(
                call: Call<ResponseSignUpEmailData>,
                response: Response<ResponseSignUpEmailData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Toast.makeText(
                        this@SignUpNameActivity,
                        "${data?.name}님 반갑습니다!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    onClickSignInActivity()
                } else {
                    Toast.makeText(this@SignUpNameActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpEmailData>, t: Throwable) {
                Log.e("NetworkTest", "error${t}")
            }
        })
    }
}