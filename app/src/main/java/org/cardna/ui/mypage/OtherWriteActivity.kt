package org.cardna.ui.mypage

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.mypage.ResponseCardStorageData
import org.cardna.databinding.ActivityOtherWriteBinding
import org.cardna.ui.mypage.adapter.OtherWriteAdapter
import org.cardna.util.SpacesItemDecoration
import org.cardna.util.SpacesItemDecorationOnlybottom
import kotlin.math.roundToInt

class OtherWriteActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOtherWriteBinding>(R.layout.activity_other_write) {
    private lateinit var adapter: OtherWriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initCoroutine()
    }

    override fun initView() {
        initCoroutine()
    }

    private fun initCoroutine() {
        lifecycleScope.launch {
            try {
                val dataContainer = ApiService.cardService.getCardBox()
                val cardList = dataContainer.data
                initFragment(cardList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initFragment(cardList: MutableList<ResponseCardStorageData.Data>) {
        adapter = OtherWriteAdapter()

        binding.rvOtherwriteList
            .addItemDecoration(SpacesItemDecorationOnlybottom((8 * resources.displayMetrics.density).roundToInt()))
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