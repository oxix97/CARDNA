import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.cardpack.CardMeList
import org.cardna.databinding.FragmentCardMeBinding
import org.cardna.ui.cardpack.adapter.CardPackMeRecyclerViewAdapter
import org.cardna.ui.maincard.DetailCardMeActivity
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt

class CardMeFragment :
    BaseViewUtil.BaseFragment<FragmentCardMeBinding>(org.cardna.R.layout.fragment_card_me) {

    private var isMyCard: Boolean = true
    var id: Int? = 4

    override fun onResume() {
        super.onResume()
        getCardMe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        //타인이 내 카드나 접근하는 경우 유저 id 받아옴
        if (getArguments() != null) { // id값이 있을 때, id값 이용해서 다른 유저의 카드나 띄우기
            Log.d("1. cardmeFragment생성시 받오오는 id:", id.toString())
            getCardMeUsingId(getArguments()?.getInt("id", 4) ?: 0)
            //내가 내 카드나 접근하는 경우
        } else {
            getCardMe()
        }
    }

    //내가 내 카드나 볼때
    private fun getCardMe() {
        lifecycleScope.launch {
            try {
                val cardMeData = ApiService.cardService.getCardMe().data.cardMeList
                isMyCard = ApiService.cardService.getCardMe().data.isMyCard
                initCardMeRvAdapter(cardMeData)

            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }


    // 다른사람의 id로 카드 팩 볼때
    private fun getCardMeUsingId(id: Int) {
        lifecycleScope.launch {
            try {
                val cardMeData = ApiService.cardService.getOtherCardMe(id).data.cardMeList
                isMyCard = ApiService.cardService.getOtherCardMe(id).data.isMyCard
                initCardMeRvAdapter(cardMeData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initCardMeRvAdapter(cardMeData: MutableList<CardMeList>) {
        Log.d("3. initCardMeRvAdapter", cardMeData.size.toString())
        var cardMeAdapter = CardPackMeRecyclerViewAdapter(cardMeData) {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java).apply {
                putExtra("id", it.id)
                putExtra("isMyCard", isMyCard)
                startActivity(this)
            }
        }

        with(binding) {
            rvCardme.adapter = cardMeAdapter
            cardMeAdapter.notifyDataSetChanged()
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            rvCardme.layoutManager = gridLayoutManager
            rvCardme.addItemDecoration(SpacesItemDecoration((12 * resources.displayMetrics.density).roundToInt()))
        }
    }

}