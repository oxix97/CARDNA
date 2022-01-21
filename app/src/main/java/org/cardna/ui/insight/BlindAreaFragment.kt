package org.cardna.ui.insight

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.insight.BlindAreaCard
import org.cardna.databinding.FragmentBlindAreaBinding
import org.cardna.ui.maincard.DetailCardMeActivity

class BuildAreaFragment(private val blindArea: BlindAreaCard) :
    BaseViewUtil.BaseFragment<FragmentBlindAreaBinding>(R.layout.fragment_blind_area) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initBlindArea()
    }

    private fun initBlindArea() {
        Glide
            .with(requireActivity())
            .load(blindArea.imageUrl)
            .into(binding.ivOpenareaImage)
        binding.tvOpenareaUserTitle.text = blindArea.title
        binding.ctlInsightCard.setOnClickListener {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java)
            intent.putExtra("id", blindArea.id)
            intent.putExtra("isMyCard", true)
            startActivity(intent)
        }
    }
}