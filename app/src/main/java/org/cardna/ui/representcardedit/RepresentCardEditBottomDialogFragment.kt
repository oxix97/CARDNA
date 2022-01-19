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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import org.cardna.R
import org.cardna.data.remote.api.ApiService
import org.cardna.data.remote.model.representcardedit.UpdateData
import org.cardna.databinding.FragmentRepresentCardEditBottomDialogBinding
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardMeAdapter
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardYouAdapter
import org.cardna.util.SpacesItemDecoration
import org.cardna.util.shortToast
import kotlin.math.roundToInt

class RepresentCardEditBottomDialogFragment(private val cardListSize: Int) :
    BottomSheetDialogFragment() {
    private val list = mutableListOf<UpdateData>()
    private var _binding: FragmentRepresentCardEditBottomDialogBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var cardMeAdapter: RepresentBottomSheetCardMeAdapter
    private lateinit var cardYouAdapter: RepresentBottomSheetCardYouAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                Log.d("ff", "ff")
                val cardMeList = cardMeContainer.data.cardMeList
                val cardYouList = cardYouContainer.data.cardYouList
                initFragment(cardMeList, cardYouList)
            } catch (e: Exception) {
                e.printStackTrace()
                requireActivity().shortToast("Coroutine error")
            }
        }
    }

    private fun initFragment(
        cardMeList: MutableList<UpdateData>,
        cardYouList: MutableList<UpdateData>
    ) {
        cardMeAdapter = RepresentBottomSheetCardMeAdapter(cardListSize)
        cardYouAdapter = RepresentBottomSheetCardYouAdapter(cardListSize)

        val gridLayoutManager1 = GridLayoutManager(requireContext(), 2)
        val gridLayoutManager2 = GridLayoutManager(requireContext(), 2)

        binding.rvRepresentcardeditCardme.layoutManager = gridLayoutManager1
        binding.rvRepresentcardeditCardyou.layoutManager = gridLayoutManager2

        binding.rvRepresentcardeditCardme.addItemDecoration(SpacesItemDecoration(12))
        binding.rvRepresentcardeditCardyou.addItemDecoration(SpacesItemDecoration(12))

        binding.rvRepresentcardeditCardme.adapter = cardMeAdapter
        binding.rvRepresentcardeditCardyou.adapter = cardYouAdapter
        binding.tvRepresentcardeditCardListCount.text = "0/${7 - cardListSize}"

        if (list.size + cardListSize < 7) {
            cardMeAdapter.setItemClickListener { position, RepresentCardData, isSelected ->
                if (isSelected) {
                    list.add(RepresentCardData)
                    cardMeAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    cardYouAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"
                    return@setItemClickListener list.lastIndex
                } else {
                    list.removeAt(position)
                    cardMeAdapter.setLastRemovedIndex(position)
                    cardYouAdapter.setLastRemovedIndex(position)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"
                    cardYouAdapter.notifyDataSetChanged()

                    return@setItemClickListener -1
                }
            }
        }

        cardYouAdapter.setItemClickListener { position, RepresentCardData, isSelected ->
            if (list.size + cardListSize < 7) {
                if (isSelected) {
                    list.add(RepresentCardData)
                    cardMeAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    cardYouAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"

                    return@setItemClickListener list.lastIndex
                } else {
                    list.removeAt(position)
                    cardMeAdapter.setLastRemovedIndex(position)
                    cardYouAdapter.setLastRemovedIndex(position)
                    binding.tvRepresentcardeditCardListCount.text =
                        "${list.size}/${7 - cardListSize}"
                    cardMeAdapter.notifyDataSetChanged()

                    return@setItemClickListener -1
                }
            } else {
                requireActivity().shortToast("대표카드는 7개가 최대입니다.")
                return@setItemClickListener 112
            }
        }

        cardMeAdapter.cardMeList.addAll(cardMeList)
        cardYouAdapter.cardYouList.addAll(cardYouList)

        cardMeAdapter.notifyDataSetChanged()
        cardYouAdapter.notifyDataSetChanged()

        onResultClick()
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
            onDestroyView()
        }
    }
}