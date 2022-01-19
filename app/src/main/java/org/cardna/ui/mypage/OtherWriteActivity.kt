package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.representcardedit.ResponseCardStorageData
import org.cardna.databinding.ActivityOtherWriteBinding
import org.cardna.ui.maincard.DetailActivity
import org.cardna.ui.mypage.adapter.OtherWriteAdapter
import org.cardna.ui.mypage.adapter.OtherWriteRecyclerViewAdapter
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt

class OtherWriteActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOtherWriteBinding>(R.layout.activity_other_write) {
    private lateinit var adapter: OtherWriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initCoroutine()
    }

    private fun initCoroutine() {
        lifecycleScope.launch {
            try {
                val dataContainer = ApiService.cardService.getCardBox()
                println("error-------------------1")
                val cardList = dataContainer.data
                initFragment(cardList)
                println("error-------------------2")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initFragment(cardList: MutableList<ResponseCardStorageData.Data>) {
        adapter = OtherWriteAdapter()

        binding.rvOtherwriteList
            .addItemDecoration(SpacesItemDecoration((12 * resources.displayMetrics.density).roundToInt()))
        binding.rvOtherwriteList.adapter = adapter
        adapter.cardList = cardList
        adapter.notifyDataSetChanged()
    }


//  data: MutableList<ResponseCardYouBoxDataList>
    // private fun initAdapter(dataList: MutableList<ResponseCardStorageData.Data>) {
    //     adapter = OtherWriteRecyclerViewAdapter(dataList) { position ->
    //         Intent(this, DetailActivity::class.java).apply {
    //             putExtra("name", position.name)
    //             putExtra("image", position.relation)
    //             putExtra("bio", position.createdAt)
    //             startActivity(this)
    //         }
    //     }
    //     with(binding) {
    //         rvOtherwriteList.adapter = adapter
    //         binding.rvOtherwriteList.layoutManager = LinearLayoutManager(this@OtherWriteActivity)
    //         adapter.notifyDataSetChanged()
    //         //     adapter.submitList(cardList) // 아이템 업데이트
    //     }
    // }
}