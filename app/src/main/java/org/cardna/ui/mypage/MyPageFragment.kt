package org.cardna.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.mypage.ResponseMyPageData
import org.cardna.data.remote.model.mypage.ResponseMyPageFriendData
import org.cardna.databinding.FragmentMyPageBinding
import org.cardna.ui.maincard.MainCardFragment
import org.cardna.ui.mypage.adapter.MyPageFriendAdapter
import org.cardna.util.SpacesItemDecoration
import org.cardna.util.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class MyPageFragment :
    BaseViewUtil.BaseFragment<FragmentMyPageBinding>(org.cardna.R.layout.fragment_my_page) {
    private lateinit var friendList: List<ResponseMyPageFriendData>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initScrollView()
        initClickEvent()
        initNetwork()
    }

    private fun initScrollView() {
        binding.scMypage.run {
            header = binding.ctlMypageHeader
        }
    }

    private fun myPageRecyclerViewAdapter(dataList: List<ResponseMyPageFriendData>) {
        val myPageFriendAdapter = MyPageFriendAdapter(dataList) { item ->
            val bundle = Bundle()
            bundle.putInt("id", item.id)
            bundle.putString("name", item.name)
            bundle.putString("sentence", item.sentence)
            // bundle.putStringArrayList("friendList", friendList)

            val mainCardFragment = MainCardFragment()
            mainCardFragment.setArguments(bundle)

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(org.cardna.R.id.fcv_main, mainCardFragment)
            transaction.commit()
        }
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMypage.layoutManager = gridLayoutManager

        binding.rvMypage.addItemDecoration(SpacesItemDecoration((16 * resources.displayMetrics.density).roundToInt()))
        binding.rvMypage.adapter = myPageFriendAdapter
        myPageFriendAdapter.notifyDataSetChanged()
    }

    private fun initClickEvent() {
        with(binding) {
            etMypageSearchBackground.setOnEditorActionListener { textView, action, event ->
                var handled = false
                if (action == EditorInfo.IME_ACTION_DONE) {
                    //검색완료 키보드 이벤트 완료 시 리사이클러뷰 item 새로 띄우기
                    requireContext().shortToast("test")
                    handled = true
                }
                handled
            }

            ivMypageGotoEmailsearch.setOnClickListener {
                startActivity(Intent(requireContext(), SearchEmailActivity::class.java))
            }
            ivMypageOtherWrote.setOnClickListener {
                startActivity(Intent(requireContext(), OtherWriteActivity::class.java))
            }
            ivMypageSetting.setOnClickListener {
                startActivity(Intent(requireContext(), SettingActivity::class.java))
            }
        }
    }

    private fun initNetwork() {
        val call: Call<ResponseMyPageData> = ApiService.myPageService.getMyPage()
        call.enqueue(object : Callback<ResponseMyPageData> {
            override fun onResponse(
                call: Call<ResponseMyPageData>,
                response: Response<ResponseMyPageData>
            ) {
                val data = response.body()?.data
                if (data != null) {
                    myPageRecyclerViewAdapter(data.friendList)
                }
                binding.tvMypageName.text = data?.name.toString()

                Glide
                    .with(this@MyPageFragment)
                    .load(data?.userImg)
                    .circleCrop()
                    .into(binding.ivMypageProfile)

                binding.tvMypageEmail.text = data?.email.toString()
                binding.tvMypageFriendCount.text = data?.friendList?.size.toString()
            }

            override fun onFailure(call: Call<ResponseMyPageData>, t: Throwable) {
                requireContext().shortToast("error")
            }
        })
    }
}