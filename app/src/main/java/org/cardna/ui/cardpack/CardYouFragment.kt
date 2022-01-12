package org.cardna.ui.cardpack

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseCardpackCardmeData
import org.cardna.data.remote.model.cardpack.ResponseCardpackCardyouData
import org.cardna.databinding.FragmentCardMeBinding
import org.cardna.databinding.FragmentCardYouBinding


class CardYouFragment :
    BaseViewUtil.BaseFragment<FragmentCardYouBinding>(org.cardna.R.layout.fragment_card_you) {

    private lateinit var cardPackRvYouAdapter: CardPackYouRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        initCardyouRvAdapter()
    }

    private fun initCardyouRvAdapter() {
        cardPackRvYouAdapter = CardPackYouRvAdapter()

        binding.rvCardyou.adapter = cardPackRvYouAdapter

        // make GridLayout
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCardyou.layoutManager = gridLayoutManager

        // Gridlayout 간격

        binding.rvCardyou.addItemDecoration(SpacesItemDecoration(12))

        cardPackRvYouAdapter.cardList.addAll(
            listOf(
                ResponseCardpackCardyouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardpackCardyouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardpackCardyouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardpackCardyouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardpackCardyouData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardpackCardyouData("img url", 1, "댕댕이 짱 좋아")
            )
        )

        cardPackRvYouAdapter.notifyDataSetChanged()
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
