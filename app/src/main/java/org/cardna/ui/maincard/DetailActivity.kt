package org.cardna.ui.maincard

import android.os.Bundle
import android.widget.Toast
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityDetailBinding

class DetailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        test()
    }

    private fun test() {
        binding.ivDetailBack.setOnClickListener {
            Toast.makeText(this, getValues(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getValues(): String {
        return "ff"
    }
}