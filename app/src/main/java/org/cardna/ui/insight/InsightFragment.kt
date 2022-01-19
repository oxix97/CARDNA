package org.cardna.ui.insight

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.insight.BlindAreaCard
import org.cardna.data.remote.model.insight.OpenAreaCard
import org.cardna.databinding.FragmentInsightBinding
import org.cardna.ui.insight.adapter.InsightAdapter
import java.lang.Exception

class InsightFragment :
    BaseViewUtil.BaseFragment<FragmentInsightBinding>(R.layout.fragment_insight) {
    private lateinit var viewPagerAdater: InsightAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initNetwork()
    }

    //viewPager연결
    private fun initAdapter(openArea : OpenAreaCard ,blindArea : BlindAreaCard) {
        val fragmentList = listOf(OpenAreaFragment(openArea), BuildAreaFragment(blindArea))
        viewPagerAdater = InsightAdapter(requireActivity())
        viewPagerAdater.fragments.addAll(fragmentList)
        binding.vpInsight.adapter = viewPagerAdater
    }

    private fun initNetwork() {
        lifecycleScope.launch {
            try {
                val dataContainer = ApiService.insightService.getInsight().data
                val openArea = dataContainer.openAreaCard
                val blindArea = dataContainer.blindAreaCard
                initAdapter(openArea,blindArea)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}