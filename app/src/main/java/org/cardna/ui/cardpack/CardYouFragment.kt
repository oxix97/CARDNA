package org.cardna.ui.cardpack

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseCardPackYouData
import org.cardna.databinding.FragmentCardYouBinding
import org.cardna.ui.cardpack.adapter.CardPackYouRecyclerViewAdapter


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
        cardYouAdapter = CardPackYouRecyclerViewAdapter()

        binding.rvCardyou.adapter = cardYouAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCardyou.layoutManager = gridLayoutManager

        binding.rvCardyou.addItemDecoration(SpacesItemDecoration(12))

        cardYouAdapter.cardList.addAll(
            listOf(
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackYouData("img url", 1, "댕댕이 짱 좋아")
            )
        )

        cardYouAdapter.notifyDataSetChanged()
    }

    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.right = space
            outRect.bottom = space
        }
    }
}
