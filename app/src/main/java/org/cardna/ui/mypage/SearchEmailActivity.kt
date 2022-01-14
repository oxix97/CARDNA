package org.cardna.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySearchEmailBinding

class SearchEmailActivity : BaseViewUtil.BaseAppCompatActivity<ActivitySearchEmailBinding>(R.layout.activity_search_email) {
    override fun initView() {
        transactionEvent()
    }

    private fun transactionEvent() {
        binding.ivSearchemailAddFriend.setOnClickListener {
            binding.ivSearchemailAddFriend.isSelected = !binding.ivSearchemailAddFriend.isSelected
      /*      if (binding.ivSearchemailAddFriend.isSelected) {
                binding.ivSearchemailAddFriend.setBackgroundResource(R.drawable.rectangle_null_white_1_5_radius_16)
            }*/
        }
    }
}