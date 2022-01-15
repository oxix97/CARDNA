package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignUpBinding

class SignUpActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailTextChanged()
        passwordTextChanged()
        onClickSignUpNameActivity()
    }

    private fun emailTextChanged() {
        binding.etSignUpEmail.addTextChangedListener {
            if (binding.etSignUpEmail.length() > 0) {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_white_1)
            } else {
                binding.clEditEmail.setBackgroundResource(R.drawable.bottom_edit_line_white_4)
            }
            setButtonBackground()
        }
    }

    private fun passwordTextChanged() {
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

    private fun onClickSignUpNameActivity() {
        binding.btnSignUpAccess.setOnClickListener {
            val intent = Intent(this, SignUpNameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
    }
}