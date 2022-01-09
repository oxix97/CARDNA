package org.cardna.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentViewPagerBinding
import org.cardna.util.MyAdapter

class ViewPagerFragment :
    BaseViewUtil.BaseFragment<FragmentViewPagerBinding>(R.layout.fragment_view_pager) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var bgColors = arrayListOf<Int>(
            R.color.black,
            R.color.mainGreen,
            R.color.mainPurple,
            R.color.purple_200,
            R.color.teal_200,
        )
        binding.vp2Container.adapter = MyAdapter(bgColors)
        binding.vp2Container.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vp2Container.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("ViewPagerFragment","Page ${position}")
            }
        })
    }
}