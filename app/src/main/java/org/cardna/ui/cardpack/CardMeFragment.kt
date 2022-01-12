import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.model.cardpack.ResponseCardPackMeData
import org.cardna.databinding.FragmentCardMeBinding
import org.cardna.ui.cardpack.adapter.CardPackMeRecyclerViewAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

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
        cardMeAdapter = CardPackMeRecyclerViewAdapter()

        binding.rvCardme.adapter = cardMeAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCardme.layoutManager = gridLayoutManager

        binding.rvCardme.addItemDecoration(SpacesItemDecoration(12))

        cardMeAdapter.cardList.addAll(
            listOf(
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아"),
                ResponseCardPackMeData("img url", 1, "댕댕이 짱 좋아")
            )
        )

        cardMeAdapter.notifyDataSetChanged()
    }

    class SpacesItemDecoration(private val space: Int) : ItemDecoration() {
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
