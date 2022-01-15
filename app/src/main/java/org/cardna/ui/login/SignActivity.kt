package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignBinding

class SignActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignBinding>(R.layout.activity_sign) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpClick()
        signInClick()
    }

    override fun initView() {
    }

    private fun signInClick() {
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpClick() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}