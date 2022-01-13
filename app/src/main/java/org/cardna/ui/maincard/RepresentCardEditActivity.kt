package org.cardna.ui.maincard

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.RepresentCardData
import org.cardna.databinding.ActivityRepresentCardEditBinding
import org.cardna.ui.cardpack.CardYouFragment
import org.cardna.ui.maincard.adapter.RepresentCardListAdapter

class RepresentCardEditActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityRepresentCardEditBinding>
        (R.layout.activity_represent_card_edit) {
    private lateinit var representCardAdapter: RepresentCardListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
        onClick()
    }

    override fun initView() {
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
            CardYouFragment.SpacesItemDecoration(12)
        )
        binding.rvRepresentcardeditContainer.adapter = representCardAdapter
        representCardAdapter.cardList.addAll(dataList)
        representCardAdapter.notifyDataSetChanged()
    }

    private fun onClick() {
        binding.fabRepresentcardedit.setOnClickListener {
            val bottomSheetView =
                layoutInflater.inflate(R.layout.fragment_represent_card_edit_bottom_dialog, null)
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
}