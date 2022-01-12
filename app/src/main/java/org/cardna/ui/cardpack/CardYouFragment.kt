package org.cardna.ui.cardpack

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentCardYouBinding


class CardYouFragment :
    BaseViewUtil.BaseFragment<FragmentCardYouBinding>(org.cardna.R.layout.fragment_card_you) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView(){
        makeRvGridLayout()
    }



    private fun makeRvGridLayout(){
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCardyou.layoutManager=  gridLayoutManager
    }
}