package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignInBinding

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
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
