import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentCardMeBinding

class CardMeFragment  :
    BaseViewUtil.BaseFragment<FragmentCardMeBinding>(org.cardna.R.layout.fragment_card_me) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        makeRvGridLayout()
    }



    private fun makeRvGridLayout(){
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCardme.layoutManager=  gridLayoutManager
    }

}