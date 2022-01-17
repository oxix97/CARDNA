package org.cardna.ui.cardpack

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.data.remote.model.cardpack.ResponseCardPackYouData
import org.cardna.databinding.FragmentCardYouBinding
import org.cardna.ui.cardpack.adapter.CardPackMeRecyclerViewAdapter
import org.cardna.ui.cardpack.adapter.CardPackYouRecyclerViewAdapter
import org.cardna.ui.maincard.DetailActivity
import org.cardna.ui.maincard.DetailCardMeActivity
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt


class CardYouFragment : BaseViewUtil.BaseFragment<FragmentCardYouBinding>(org.cardna.R.layout.fragment_card_you) {

    private lateinit var cardYouAdapter: CardPackYouRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        initCardYouRvAdapter()
    }

    private fun initCardYouRvAdapter() {
        cardYouAdapter = CardPackYouRecyclerViewAdapter(
            listOf(
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아")
            )
        ) {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java).apply {
                putExtra("cardImg", R.drawable.background_cardyou)
                putExtra("id", 1)
                putExtra("title", "테스트~~")
                startActivity(this)
            }
        }

        with(binding) {
            rvCardyou.adapter = cardYouAdapter

            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            rvCardyou.layoutManager = gridLayoutManager

            rvCardyou.addItemDecoration(SpacesItemDecoration((12 * resources.displayMetrics.density).roundToInt()))
        }
    }
}
