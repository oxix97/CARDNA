import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.FragmentCardMeBinding
import org.cardna.ui.cardpack.CardPackFragment
import org.cardna.ui.cardpack.adapter.CardPackMeRecyclerViewAdapter
import org.cardna.ui.maincard.DetailActivity
import org.cardna.ui.maincard.DetailCardMeActivity
import org.cardna.ui.maincard.MainCardFragment
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt

class CardMeFragment : BaseViewUtil.BaseFragment<FragmentCardMeBinding>(org.cardna.R.layout.fragment_card_me) {

    private lateinit var cardMeAdapter: CardPackMeRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        initCardMeRvAdapter()
    }

    private fun initCardMeRvAdapter() {
        cardMeAdapter = CardPackMeRecyclerViewAdapter(
            listOf(
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아")
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
            rvCardme.adapter = cardMeAdapter

            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            rvCardme.layoutManager = gridLayoutManager

            rvCardme.addItemDecoration(SpacesItemDecoration((12 * resources.displayMetrics.density).roundToInt()))
        }
    }
}
