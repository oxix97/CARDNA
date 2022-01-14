package org.cardna.ui.mypage

import android.os.Bundle
import android.view.View
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivitySearchEmailBinding

class SearchEmailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySearchEmailBinding>(R.layout.activity_search_email) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transactionEvent()
    }

    override fun initView() {
    }

    private fun transactionEvent() {
        binding.ivSearchemailAddFriend.setOnClickListener {
            binding.ivSearchemailAddFriend.isSelected = !binding.ivSearchemailAddFriend.isSelected
            if (binding.ivSearchemailAddFriend.isSelected) {
                binding.tvSearchemailCheckedAddFriend.visibility = View.VISIBLE
                binding.ivSearchemailCheckImage.visibility = View.VISIBLE

                binding.tvSearchemailAddFriend.visibility = View.GONE
            } else {
                binding.tvSearchemailAddFriend.visibility = View.VISIBLE

                binding.tvSearchemailCheckedAddFriend.visibility = View.GONE
                binding.ivSearchemailCheckImage.visibility = View.GONE
            }

            /*      if (binding.ivSearchemailAddFriend.isSelected) {
                      binding.ivSearchemailAddFriend.setBackgroundResource(R.drawable.rectangle_null_white_1_5_radius_16)
                  }*/
        }
    }
}