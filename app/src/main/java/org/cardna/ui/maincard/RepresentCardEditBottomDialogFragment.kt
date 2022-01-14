package org.cardna.ui.maincard

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.RepresentCardData
import org.cardna.databinding.FragmentRepresentCardEditBottomDialogBinding
import org.cardna.ui.cardpack.CardYouFragment
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardMeAdapter
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardYouAdapter

class RepresentCardEditBottomDialogFragment :
    BaseViewUtil.BaseFragment<FragmentRepresentCardEditBottomDialogBinding>
        (R.layout.fragment_represent_card_edit_bottom_dialog) {
    private lateinit var cardMeAdapter: RepresentBottomSheetCardMeAdapter
    private lateinit var cardYouAdapter: RepresentBottomSheetCardYouAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        val dataList = listOf(
            RepresentCardData(
                R.drawable.dummy_img_test,
                "ㅁ너ㅣㅏㄹㅁㄴ",
                R.color.main_green,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "fsdafs",
                R.color.main_purple,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "ㅁ너ㅣㅏㄹㅁㄴ",
                R.color.main_green,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "ㅁㄹㄹㄹㄹㄹㄹ",
                R.color.main_purple,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "ㅁ너ㅣㅏㄹㅁㄴ",
                R.color.main_green,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "fsdafs",
                R.color.main_purple,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "ㅁ너ㅣㅏㄹㅁㄴ",
                R.color.main_green,
            ),
            RepresentCardData(
                R.drawable.dummy_img_test,
                "ㅁㄹㄹㄹㄹㄹㄹ",
                R.color.main_purple,
            ),
        )
        cardMeAdapter = RepresentBottomSheetCardMeAdapter()
        cardYouAdapter = RepresentBottomSheetCardYouAdapter()

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvRepresentcardeditCardme.layoutManager = gridLayoutManager
        binding.rvRepresentcardeditCardyou.layoutManager = gridLayoutManager

        binding.rvRepresentcardeditCardme.addItemDecoration(CardYouFragment.SpacesItemDecoration(12))
        binding.rvRepresentcardeditCardyou.addItemDecoration(CardYouFragment.SpacesItemDecoration(12))

        binding.rvRepresentcardeditCardme.adapter = cardMeAdapter
        binding.rvRepresentcardeditCardyou.adapter = cardYouAdapter

        cardMeAdapter.notifyDataSetChanged()
        cardYouAdapter.notifyDataSetChanged()

        onResultClick()
    }

    private fun onResultClick() {
        binding.tvRepresentcardeditFinish.setOnClickListener {

        }
    }

    override fun initView() {
    }
}