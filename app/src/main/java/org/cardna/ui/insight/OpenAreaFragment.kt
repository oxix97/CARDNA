package org.cardna.ui.insight

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.insight.OpenAreaCard
import org.cardna.databinding.FragmentOpenAreaBinding
import org.cardna.ui.maincard.DetailCardMeActivity

class OpenAreaFragment(private val openArea: OpenAreaCard) :
    BaseViewUtil.BaseFragment<FragmentOpenAreaBinding>(R.layout.fragment_open_area) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initOpenArea()
    }

    private fun initOpenArea() {
        Glide
            .with(requireActivity())
            .load(openArea.imageUrl)
            .into(binding.ivOpenareaImage)
        binding.tvOpenareaUserTitle.text = openArea.title
        binding.ctlInsightCard.setOnClickListener {
            val intent = Intent(requireContext(),DetailCardMeActivity::class.java)
            intent.putExtra("id",openArea.id)
            startActivity(intent)
        }
    }
}