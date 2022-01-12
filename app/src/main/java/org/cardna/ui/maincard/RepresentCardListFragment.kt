package org.cardna.ui.maincard

import android.os.Bundle
import android.view.View
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentRepresentCardListBinding
import org.cardna.ui.maincard.adapter.RepresentCardListAdapter

class RepresentCardListFragment :
    BaseViewUtil.BaseFragment<FragmentRepresentCardListBinding>(R.layout.fragment_represent_card_list) {
    private lateinit var representCardAdapter: RepresentCardListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {

    }
}