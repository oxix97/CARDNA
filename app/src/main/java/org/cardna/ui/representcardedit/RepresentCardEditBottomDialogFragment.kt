package org.cardna.ui.representcardedit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.maincard.RequestMainCardEditData
import org.cardna.data.remote.model.representcardedit.UpdateData
import org.cardna.databinding.FragmentRepresentCardEditBottomDialogBinding
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardMeAdapter
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardYouAdapter
import org.cardna.util.SpacesItemDecoration
import org.cardna.util.shortToast
import kotlin.math.roundToInt

//기존 수정된 representcardlist의 card id와 총개수를 생성자로 받음
class RepresentCardEditBottomDialogFragment(
    private var representCard: MutableList<Int>,  //representcardlist의 card id리스트->id비교해서 안그려줄려고
    private val cardListSize: Int,  //representacardlist엿던 iteme들의 총개수->총 7개까지 지정하는 부분에서 뺴려고
) :
    BottomSheetDialogFragment() {
    private val list = mutableListOf<UpdateData>()
    private var _binding: FragmentRepresentCardEditBottomDialogBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var cardMeAdapter: RepresentBottomSheetCardMeAdapter
    private lateinit var cardYouAdapter: RepresentBottomSheetCardYouAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //수정아이콘 물럿을 때 렐이아웃 처리
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED

        binding.clBottomSheet.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.94).roundToInt()
        initCoroutine()
        initTabLayout()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_represent_card_edit_bottom_dialog,
            container,
            false
        )
        return binding.root
    }

    private fun initCoroutine() {
        lifecycleScope.launch {
            try {
                //코루틴 에러가 나는데 ??
                val cardMeContainer = ApiService.cardService.getBottomSheetCardMe()
                val cardYouContainer = ApiService.cardService.getBottomSheetCardYou()

                //처음에 기존에 있던 카드나, 카드너 리스트를 서버통신으로 불러옴
                val cardMeList = cardMeContainer.data.cardMeList
                val cardYouList = cardYouContainer.data.cardYouList

                //카드나, 카드너를 새롭게 담을 빈 리스트를 만듬
                val meList = mutableListOf<UpdateData>()
                val youList = mutableListOf<UpdateData>()

                //카드나 대표카드 제거 버전=> meList
                for (i in 0 until cardMeList.size) {
                    if (!representCard.contains(cardMeList[i].id)) {
                        meList.add(cardMeList[i])
                    }
                }

                //카드너 대표카드 제거 버전=> youList
                for (i in 0 until cardYouList.size) {
                    if (!representCard.contains(cardYouList[i].id)) {
                        youList.add(cardYouList[i])
                    }
                }

                initFragment(meList, youList)
            } catch (e: Exception) {
                e.printStackTrace()
                requireActivity().shortToast("Coroutine error")
            }
        }
    }

    private fun initFragment(
        //대표카드가 들어있지않은 카드나, 카드너
        cardMeList: MutableList<UpdateData>,
        cardYouList: MutableList<UpdateData>
    ) {
        //바텀싯 어뎁터에 대표카드 리스트랑,
        cardMeAdapter = RepresentBottomSheetCardMeAdapter(cardListSize, representCard) //바텀싯 카드나 어댑터한테 대표카드 사이즈랑 대표카드들 id리스트 보냄
        cardYouAdapter = RepresentBottomSheetCardYouAdapter(cardListSize, representCard)//바텀싯 카드너 어댑터한테 대표카드 사이즈랑 대표카드들 id리스트 보냄

        val gridLayoutManager1 = GridLayoutManager(requireContext(), 2)
        val gridLayoutManager2 = GridLayoutManager(requireContext(), 2)

        binding.rvRepresentcardeditCardme.layoutManager = gridLayoutManager1
        binding.rvRepresentcardeditCardyou.layoutManager = gridLayoutManager2

        binding.rvRepresentcardeditCardme.addItemDecoration(SpacesItemDecoration(12))
        binding.rvRepresentcardeditCardyou.addItemDecoration(SpacesItemDecoration(12))

        binding.rvRepresentcardeditCardme.adapter = cardMeAdapter
        binding.rvRepresentcardeditCardyou.adapter = cardYouAdapter
        binding.tvRepresentcardeditCardListCount.text = "0/${7 - cardListSize}"  //앞에서 넘겨준

        cardMeAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

        })

        if (list.size < 7) {
            cardMeAdapter.setItemClickListener { position, RepresentCardData, isSelected ->
                if (isSelected) {
                    list.add(RepresentCardData)
                    cardMeAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    cardYouAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"
                    cardYouAdapter.notifyDataSetChanged()
                    return@setItemClickListener list.lastIndex
                } else {
                    Log.d("size",list.size.toString())
                    Log.d("position",position.toString())
                    list.removeAt(position)
                    cardMeAdapter.setLastRemovedIndex(position)
                    cardYouAdapter.setLastRemovedIndex(position)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"

                    cardYouAdapter.notifyDataSetChanged()
                    cardMeAdapter.notifyDataSetChanged()
                    return@setItemClickListener -1
                }
            }
            cardYouAdapter.setItemClickListener { position, RepresentCardData, isSelected ->
                if (isSelected) {
                    list.add(RepresentCardData)
                    cardMeAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    cardYouAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"
                    cardMeAdapter.notifyDataSetChanged()
                    cardYouAdapter.notifyDataSetChanged()
                    return@setItemClickListener list.lastIndex
                } else {
                    Log.d("size",list.size.toString())
                    Log.d("position",position.toString())
                    list.removeAt(position)
                    cardMeAdapter.setLastRemovedIndex(position)
                    cardYouAdapter.setLastRemovedIndex(position)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"
                    cardMeAdapter.notifyDataSetChanged()
                    cardYouAdapter.notifyDataSetChanged()
                    return@setItemClickListener -1
                }
            }
        } else {
            requireActivity().shortToast("대표카드는 7개가 최대입니다.")
        }
        cardMeAdapter.cardMeList.addAll(cardMeList)
        cardYouAdapter.cardYouList.addAll(cardYouList)

        cardMeAdapter.notifyDataSetChanged()
        cardYouAdapter.notifyDataSetChanged()

        onResultClick()
    }

    private fun listCounter(count: Int) {
    }

    private fun initTabLayout() {
        binding.tlRepresentcardedit.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.rvRepresentcardeditCardme.isVisible = true
                        binding.rvRepresentcardeditCardyou.isVisible = false
                    }
                    1 -> {
                        binding.rvRepresentcardeditCardme.isVisible = false
                        binding.rvRepresentcardeditCardyou.isVisible = true
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onResultClick() {
        binding.tvRepresentcardeditFinish.setOnClickListener {
            for (i in 0 until cardMeAdapter.pickUpList.size) {
                representCard.add(cardMeAdapter.pickUpList[i].id)
                Log.d("CardMe Adapter List :", "${cardMeAdapter.pickUpList[i].id}")
            }
            for (i in 0 until cardYouAdapter.pickUpList.size) {
                representCard.add(cardYouAdapter.pickUpList[i].id)
                Log.d("CardMe Adapter List :", "${cardYouAdapter.pickUpList[i].id}")
            }
            val totalData = RequestMainCardEditData(representCard)
            representPutData(totalData)
        }
    }

    private fun representPutData(data: RequestMainCardEditData) {
        lifecycleScope.launch {
            ApiService.cardService.putMainCardEdit(data)
        }
        data.cards.forEach {
            Log.d("이미지 아이디", it.toString())
        }
        dialog?.dismiss()
    }
}