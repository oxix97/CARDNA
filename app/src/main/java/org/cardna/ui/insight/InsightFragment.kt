package org.cardna.ui.insight

import android.os.Bundle
import android.view.View
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentInsightBinding
import org.cardna.ui.insight.adapter.InsightAdapter

class InsightFragment : BaseViewUtil.BaseFragment<FragmentInsightBinding>(R.layout.fragment_insight) {
    private lateinit var viewPagerAdater: InsightAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter()
    }

    //viewPager연결
    private fun initAdapter() {
        val fragmentList = listOf(OpenAreaFragment(), BlindAreaFragment())
        viewPagerAdater = InsightAdapter(requireActivity())
        viewPagerAdater.fragments.addAll(fragmentList)
        binding.vpInsight.adapter = viewPagerAdater
    }
}