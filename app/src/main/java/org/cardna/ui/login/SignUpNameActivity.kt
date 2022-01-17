package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignUpNameBinding

class SignUpNameActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpNameBinding>(R.layout.activity_sign_up_name) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        nameTextChanged()
        onClickSignInActivity()
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
}