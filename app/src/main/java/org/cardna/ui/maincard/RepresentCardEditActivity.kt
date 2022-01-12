package org.cardna.ui.maincard

import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.RepresentCardData
import org.cardna.databinding.ActivityMainBinding
import org.cardna.ui.maincard.adapter.RepresentCardListAdapter

class RepresentCardEditActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_represent_card_edit) {
    private lateinit var representCardAdapter: RepresentCardListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
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
        )
        representCardAdapter = RepresentCardListAdapter()
        representCardAdapter.cardList.addAll(dataList)
        representCardAdapter.notifyDataSetChanged()
    }
}