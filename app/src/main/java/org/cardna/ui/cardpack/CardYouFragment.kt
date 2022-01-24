package org.cardna.ui.cardpack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.cardpack.CardYouList
import org.cardna.databinding.FragmentCardYouBinding
import org.cardna.ui.cardpack.adapter.CardPackYouRecyclerViewAdapter
import org.cardna.ui.maincard.DetailCardMeActivity
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt


class CardYouFragment : BaseViewUtil.BaseFragment<FragmentCardYouBinding>(org.cardna.R.layout.fragment_card_you) {
    private var isMyCard: Boolean = true
    var id: Int? = 4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        if (getArguments() != null) { // id값이 있을 때, id값 이용해서 다른 유저의 카드나 띄우기
            getCardYouUsingId(getArguments()?.getInt("id", 4) ?: 0)
            //내가 내 카드나 접근하는 경우
        } else {
            getCardYou()
        }
    }

    //내가 내 카드너 볼때
    private fun getCardYou() {
        lifecycleScope.launch {
            try {
                val cardMeData = ApiService.cardService.getCardYou().data.cardYouList
                isMyCard = ApiService.cardService.getCardYou().data.isMyCard
                initCardYouRvAdapter(cardMeData)

            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    private fun getCardYouUsingId(id: Int) {
        lifecycleScope.launch {
            try {
                val cardMeData = ApiService.cardService.getOtherCardYou(id).data.cardYouList
                isMyCard = ApiService.cardService.getOtherCardYou(id).data.isMyCard
                initCardYouRvAdapter(cardMeData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initCardYouRvAdapter(cardYouData: MutableList<CardYouList>) {
        var cardMeAdapter = CardPackYouRecyclerViewAdapter(cardYouData) {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java).apply {
                putExtra("id", it.id)
                putExtra("isMyCard", isMyCard)
                startActivity(this)
            }
        }

        with(binding) {
            rvCardyou.adapter = cardMeAdapter
            cardMeAdapter.notifyDataSetChanged()
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            rvCardyou.layoutManager = gridLayoutManager
            rvCardyou.addItemDecoration(SpacesItemDecoration((12 * resources.displayMetrics.density).roundToInt()))
        }
    }
}
