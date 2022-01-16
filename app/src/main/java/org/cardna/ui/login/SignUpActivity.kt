package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val emailValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val passwordValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailTextChanged()
        passwordTextChanged()
        onClickSignUpNameActivity()
    }

    private fun emailTextChanged() {
        binding.etSignUpEmail.addTextChangedListener {
            checkEmail()
            if (binding.etSignUpEmail.length() > 0) {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
            } else {
                binding.clEditEmail.setBackgroundResource(R.drawable .bottom_edit_line_white_4)
            }
            setButtonBackground()
        }
    }

    private fun passwordTextChanged() {
        passwordCheck()
        binding.etSignUpPassword.addTextChangedListener {
            if (binding.etSignUpPassword.length() > 0) {
                binding.clEditPassword.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
            } else {
                binding.clEditPassword.setBackgroundResource(R.drawable.bottom_edit_line_white_4)
            }
            setButtonBackground()
        }
    }

    private fun setButtonBackground() {

        if (binding.etSignUpEmail.length() > 0 && binding.etSignUpPassword.length() > 0) {
            binding.btnSignUpAccess.setBackgroundResource(R.drawable.rectangle_sign_in_gradient_radius_10)
            binding.btnSignUpAccess.setTextColor(getColor(R.color.black))
        } else {
            binding.btnSignUpAccess.setBackgroundResource(R.drawable.rectangle_grey_radius_10)
            binding.btnSignUpAccess.setTextColor(getColor(R.color.white_2))
        }
    }

    private fun checkEmail(): Boolean {
        val email = binding.etSignUpEmail.text.toString().trim() //공백제거
        val p = Pattern.matches(emailValidation, email) // 패턴 확인
        return if (p) {
            //이메일 형태가 정상일 경우
            binding.ivSignupEmailCheck.setImageResource(R.drawable.ic_success)
            true
        } else {
            binding.ivSignupEmailCheck.setImageResource(R.drawable.ic_invalid)
            false
        }
    }

    private fun passwordCheck(): Boolean {
        val password = binding.etSignUpPassword.text.toString().trim() //공백제거
        val p = Pattern.matches(passwordValidation, password) // 패턴 확인
        return if (p) {
            binding.ivSignupPasswordCheck.setImageResource(R.drawable.ic_success)
            true
        } else {
            binding.ivSignupPasswordCheck.setImageResource(R.drawable.ic_invalid)
            false
        }
    }

    private fun onClickSignUpNameActivity() {
        binding.btnSignUpAccess.setOnClickListener {
            val intent = Intent(this, SignUpNameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
    }
}