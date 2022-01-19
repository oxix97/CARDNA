package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.mypage.ResponseCardYouBoxDataList
import org.cardna.databinding.ActivityOtherWriteBinding
import org.cardna.ui.maincard.DetailActivity
import org.cardna.ui.mypage.adapter.OtherWriteRecyclerViewAdapter

class OtherWriteActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOtherWriteBinding>(R.layout.activity_other_write) {

    private lateinit var dataContainer:MutableList<ResponseCardYouBoxDataList>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initCoroutine()
        Log.d("하아",dataContainer.size.toString())
        initAdapter()
    }
  //  data: MutableList<ResponseCardYouBoxDataList>
    private fun initAdapter() {
        val adapter = OtherWriteRecyclerViewAdapter(dataContainer) { position ->
            Intent(this, DetailActivity::class.java).apply {
                putExtra("name", position.name)
                putExtra("image", position.relation)
                putExtra("bio", position.createdAt)
                startActivity(this)
            }
        }
        with(binding) {
            rvOtherwriteList.adapter = adapter
            binding.rvOtherwriteList.layoutManager = LinearLayoutManager(this@OtherWriteActivity)
            adapter.notifyDataSetChanged()
       //     adapter.submitList(cardList) // 아이템 업데이트
        }
    }

    private fun initCoroutine() {
        lifecycleScope.launch {
            try {
                dataContainer = ApiService.cardService.getCardBox().responseCardYouBoxDataList

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }



}