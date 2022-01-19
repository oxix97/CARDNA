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

    private lateinit var cardMeAdapter: CardPackMeRecyclerViewAdapter
    private var totalCardCnt: Int = 0
    private var isMyCard: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  initView()
        getCardMe()
    }

    override fun initView() {
     //   initCardMeRvAdapter()
    }

    // 이 블록의 내용들이 바텀 내비게이션이 탭 될때마다 실행되야하지 않을까

  /*  init { // CardPackFragment로부터 생성될 건데 거기서 받은 id 값에 따라 뿌려주는 data가 다름.
        var id: Int?
        if (getArguments() != null) { // id값이 있을 때, id값 이용해서 다른 유저의 카드나 띄우기
            id = getArguments()?.getInt("id", 0) ?: 0
            //     getCardMeUsingId(id)
        } else { // id값이 없을 때, id값 이용해서 나의 카드나 띄우기
            getCardMe()
        }
    }*/


    // 현재 유저의 카드나 띄우기
    private fun getCardMe() {
        lifecycleScope.launch {
            try {
              val  cardMeData = ApiService.cardService.getCardMe().data.cardMeList
                //      Toast.makeText(requireActivity(), cardMeData.cardMeList.size.toString(), Toast.LENGTH_SHORT).show()
                //  cardMeList = cardMeData.cardMeList
                /*  totalCardCnt = cardMeData.cardMeList.size
                  isMyCard = cardMeData.isMyCard*/
                // 가지고 현재 fragment에 띄우기
                initCardMeRvAdapter(cardMeData)
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }

    // 다른 유저의 카드나 띄우기
/*    private fun getCardMeUsingId(id: Int) {
        lifecycleScope.launch {
            try {
                val cardMeData = ApiService.cardService.getOtherCardMe(id).data
                cardMeList = cardMeData.cardMeList
                totalCardCnt = cardMeData.totalCardCnt
                isMyCard = cardMeData.isMyCard
                // 가지고 현재 fragment에 띄우기
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }
    }*/


    private fun initCardMeRvAdapter(cardMeData:MutableList<CardMeList>) {
        // 여기서 서버에서 받아온 cardMeList에 대한 data를 뿌려줘야함
        // getCardMe로 받아왔다면 cardMeList의 isMyCard 가 true일 것이고,
        // getOtherCardMe로 받아옸다면 cardMeList의 isMyCard가 false일 것이다.
        // 내가 쓰진 않지만, 눌렀을 때 이를 넘겨줘야 함.

        /*       val cardListForRecyclerView = mutableListOf<ResponseCardAllData.Data.CardMe>()

               for (i in 0 until (totalCardCnt)) {
                   cardListForRecyclerView.add(cardMeList[i])
               }*/

        cardMeAdapter = CardPackMeRecyclerViewAdapter(
            cardMeData
        ) {
            val intent = Intent(requireContext(), DetailCardMeActivity::class.java).apply {
                // cardId, isMe만 넘겨주면 DetailActivity에서 cardId가지고 서버 통신하면 됨
                if (it.id != null) {
                    putExtra("cardId", it.id)
                }
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
