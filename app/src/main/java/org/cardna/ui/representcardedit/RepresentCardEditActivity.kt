package org.cardna.ui.representcardedit

import android.os.Bundle
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.RepresentCardData
import org.cardna.databinding.ActivityRepresentCardEditBinding
import org.cardna.ui.maincard.adapter.RepresentCardListAdapter
import org.cardna.util.LinearGradientSpan
import org.cardna.util.SpacesItemDecoration

class RepresentCardEditActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityRepresentCardEditBinding>
        (R.layout.activity_represent_card_edit) {
    private lateinit var representCardAdapter: RepresentCardListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initFragment()
        onClick()
        setTextGradient()
        onClickResult()
        representCardCount()
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
        representCardAdapter = RepresentCardListAdapter()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvRepresentcardeditContainer.layoutManager = gridLayoutManager
        binding.rvRepresentcardeditContainer.addItemDecoration(
            SpacesItemDecoration(6)
        )
        binding.rvRepresentcardeditContainer.adapter = representCardAdapter
        representCardAdapter.cardList.addAll(dataList)
        representCardAdapter.notifyDataSetChanged()
    }

    private fun onClick() {
        binding.fabRepresentcardedit.setOnClickListener {
            val bottomSheetDialog = RepresentCardEditBottomDialogFragment()

            bottomSheetDialog.show(supportFragmentManager, "sdsfs")
        }
    }

    private fun setTextGradient() {
        val text = binding.tvRepresentcardeditColorTitle.text.toString()
        val green = getColor(R.color.main_green)
        val purple = getColor(R.color.main_purple)
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, green, purple)
        binding.tvRepresentcardeditColorTitle.text = spannable
    }

    private fun onClickResult() {
        binding.tvTvRepresentcardeditFinish.setOnClickListener {
            finish()
        }
    }

    private fun representCardCount() {
        val countText = "${representCardAdapter.cardList.size}/7"
        binding.tvRepresentcardeditCardListCount.text = countText
        representCardAdapter.notifyDataSetChanged()
    }
}