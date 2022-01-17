package org.cardna.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.text.set
import androidx.core.text.toSpannable
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignBinding
import org.cardna.util.LinearGradientSpan

class SignActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignBinding>(R.layout.activity_sign) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        signUpClick()
        signInClick()
        setTextGradient()
    }

    private fun setTextGradient() {
        val text = binding.btnSignUp.text.toString()
        val green = getColor(R.color.main_green)
        val purple = getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.btnSignUp.text = spannable
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