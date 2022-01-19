package org.cardna.ui.representcardedit

import android.os.Bundle
import android.util.Log
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.maincard.MainCardList
import org.cardna.data.remote.model.maincard.RequestMainCardEditData
import org.cardna.databinding.ActivityRepresentCardEditBinding
import org.cardna.ui.maincard.adapter.RepresentCardListAdapter
import org.cardna.util.LinearGradientSpan
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt

class RepresentCardEditActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityRepresentCardEditBinding>
        (R.layout.activity_represent_card_edit) {
    private lateinit var representCardAdapter: RepresentCardListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initCoroutine()
    }

    override fun initView() {
        initCoroutine()
        onClick()
        setTextGradient()
        onClickResult()
    }

    private fun initFragment(representList: MutableList<MainCardList>) {
        representCardAdapter = RepresentCardListAdapter()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvRepresentcardeditContainer.layoutManager = gridLayoutManager

        binding.rvRepresentcardeditContainer
            .addItemDecoration(SpacesItemDecoration((12 * resources.displayMetrics.density).roundToInt()))

        binding.rvRepresentcardeditContainer.adapter = representCardAdapter
        representCardAdapter.cardList = representList
        representCardAdapter.notifyDataSetChanged()
    }

    private fun onClick() {
        binding.fabRepresentcardedit.setOnClickListener {
            val bottomSheetDialog =
                RepresentCardEditBottomDialogFragment(representCardAdapter.cardList.size)

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
            val cardList = representCardAdapter.cardList
            val cardIdList = mutableListOf<Int>()

            for (i in 0 until cardList.size) {
                cardIdList.add(cardList[i].id)
            }

            val data = RequestMainCardEditData(cardIdList)
            lifecycleScope.launch {
                try {
                    ApiService.cardService.putMainCardEdit(data)
                    ApiService.cardService.getUserMainCard()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            finish()
        }
    }

    private fun initCoroutine() {
        lifecycleScope.launch {
            try {
                val dataContainer = ApiService.cardService.getUserMainCard()
                val list = dataContainer.data.mainCardList
                initFragment(list)
            } catch (e: Exception) {
                Log.d("error", "error")
            }
        }
    }
}