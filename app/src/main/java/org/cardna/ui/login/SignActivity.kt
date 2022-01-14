package org.cardna.ui.login

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySignBinding

class SignActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignBinding>(R.layout.activity_sign) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Glide
            .with(this)
            .load(R.drawable.ic_logo)
            .into(binding.ivSignLogo)

        // val shader: Shader = LinearGradient(
        //     0f, 0f, 20f, 20f, listOf<Long>(
        //         R.color.main_green.toLong(),
        //         R.color.main_purple.toLong(),
        //     ), 20f, 0.1, Shader.TileMode.CLAMP
        // )
    }

    override fun initView() {
    }
}