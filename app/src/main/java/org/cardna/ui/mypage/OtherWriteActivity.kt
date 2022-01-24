package org.cardna.ui.mypage

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var flag = false


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
        binding.rvOtherwriteList
            .addItemDecoration(SpacesItemDecorationOnlybottom((12 * resources.displayMetrics.density).roundToInt()))
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
        binding.rvOtherwriteList.adapter = adapter
        adapter.cardList = cardList
        adapter.notifyDataSetChanged()
    }

}