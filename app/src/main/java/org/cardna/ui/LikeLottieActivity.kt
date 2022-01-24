package org.cardna.ui

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityLikeLottieBinding


class LikeLottieActivity : BaseViewUtil.BaseAppCompatActivity<ActivityLikeLottieBinding>(org.cardna.R.layout.activity_like_lottie) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {


        binding.lottie.setAnimation("lottie_cardme_1.json")
        binding.lottie.loop(true)
        binding.lottie.playAnimation()
        binding.lottie.setRepeatCount(2);


        binding.lottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                binding.lottie.setVisibility(View.GONE)
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }


}

