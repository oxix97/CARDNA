package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.login.RequestSignInEmailData
import org.cardna.data.remote.model.login.ResponseSignInEmailData
import org.cardna.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        emailTextChanged()
        passwordTextChanged()
        onMainActivity()
    }

    private fun emailTextChanged() {
        binding.etSignInEmail.addTextChangedListener {
            if (binding.etSignInEmail.length() > 0) {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
            } else {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_white_4)
            }
            setButtonBackground()
        }
    }

    private fun passwordTextChanged() {
        binding.etSignInPassword.addTextChangedListener {
            if (binding.etSignInPassword.length() > 0) {
                binding.clEditPassword.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
            } else {
                binding.clEditPassword.setBackgroundResource(R.drawable.bottom_edit_line_white_4)
            }
            setButtonBackground()
        }
    }
    private fun setButtonBackground() {
        if (binding.etSignInEmail.length() > 0 && binding.etSignInPassword.length() > 0) {
            binding.btnSignInAccess.setBackgroundResource(R.drawable.rectangle_sign_in_gradient_radius_10)
            binding.btnSignInAccess.setTextColor(getColor(R.color.black))
        } else {
            binding.btnSignInAccess.setBackgroundResource(R.drawable.rectangle_grey_radius_10)
            binding.btnSignInAccess.setTextColor(getColor(R.color.white_2))
        }
    }

    private fun onMainActivity() {
        binding.btnSignInAccess.setOnClickListener {
            initNetwork()
        }
    }

    private fun initNetwork() {
        val requestLoginData = RequestSignInEmailData(
            email = binding.etSignInEmail.text.toString(),
            password = binding.etSignInPassword.text.toString(),
        )
        val call: Call<ResponseSignInEmailData> =
            ApiService.authService.postSignIn(requestLoginData)

        call.enqueue(object : Callback<ResponseSignInEmailData> {
            override fun onResponse(
                call: Call<ResponseSignInEmailData>,
                response: Response<ResponseSignInEmailData>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignInEmailData>, t: Throwable) {
                Log.e("NetworkTest", "error$t")
            }
        })
    }
}
