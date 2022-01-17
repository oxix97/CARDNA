package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
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
        "^(?=.*[a-zA-z])(?=.*[0-9]).{8,99}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun onClickAccess() {
        if (checkEmail() && passwordCheck()) {
            binding.btnSignUpAccess.isEnabled = true
            binding.btnSignUpAccess.setBackgroundResource(R.drawable.rectangle_sign_in_gradient_radius_10)
            binding.btnSignUpAccess.setTextColor(getColor(R.color.white_1))
        } else {
            binding.btnSignUpAccess.isEnabled = false
            binding.btnSignUpAccess.setBackgroundResource(R.drawable.rectangle_sign_up_gray_radius_10)
        }
    }

    private fun emailTextChanged() {
        val email = binding.etSignUpEmail
        email.addTextChangedListener {
            if (checkEmail()) {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
                binding.llSignupEmailLayout.isVisible = false
            } else {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_red)
                binding.llSignupEmailLayout.isVisible = true
            }
            onClickAccess()
        }
    }

    private fun passwordTextChange() {
        val password = binding.etSignUpPassword
        password.addTextChangedListener {
            if (passwordCheck()) {
                binding.llSignupEmailLayout.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
                binding.llSignupPasswordLayout.isVisible = false
                binding.ivSignupPasswordCheck.isVisible = true
            } else {
                binding.llSignupPasswordLayout.setBackgroundResource(R.drawable.bottom_edit_line_red)
                binding.llSignupPasswordLayout.isVisible = true
            }
            onClickAccess()
        }
    }

    private fun checkEmail(): Boolean {
        val email = binding.etSignUpEmail.text.toString().trim() //공백제거
        val p = Pattern.matches(emailValidation, email) // 패턴 확인
        return p
    }

    private fun passwordCheck(): Boolean {
        val password = binding.etSignUpPassword.text.toString().trim() //공백제거
        val p = Pattern.matches(passwordValidation, password) // 패턴 확인
        return p
    }

    //여기서 true, true
    private fun onClickSignUpNameActivity() {
        binding.btnSignUpAccess.setOnClickListener {
            val intent = Intent(this, SignUpNameActivity::class.java)
            intent.putExtra("email",binding.etSignUpEmail.text.toString())
            intent.putExtra("password",binding.etSignUpPassword.text.toString())
            startActivity(intent)
        }
    }

    override fun initView() {
        emailTextChanged()
        passwordTextChange()
        onClickSignUpNameActivity()
    }
}
