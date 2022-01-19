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
import org.cardna.data.remote.model.representcardedit.RepresentCardMeData
import org.cardna.data.remote.model.representcardedit.RepresentCardMeListData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouListData
import org.cardna.databinding.FragmentRepresentCardEditBottomDialogBinding
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardMeAdapter
import org.cardna.ui.maincard.adapter.RepresentBottomSheetCardYouAdapter
import org.cardna.util.SpacesItemDecoration
import kotlin.math.roundToInt

class RepresentCardEditBottomDialogFragment :
    BottomSheetDialogFragment() {
    private val list1 = mutableListOf<RepresentCardMeData>()
    private val list2 = mutableListOf<RepresentCardYouData>()
    private var _binding: FragmentRepresentCardEditBottomDialogBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private lateinit var cardMeAdapter: RepresentBottomSheetCardMeAdapter
    private lateinit var cardYouAdapter: RepresentBottomSheetCardYouAdapter

    private lateinit var cardMeList: MutableList<RepresentCardMeListData>
    private lateinit var cardYouList: MutableList<RepresentCardYouListData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED

        binding.clBottomSheet.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.94).roundToInt()
        initFragment()
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

    private fun initFragment() {
        lifecycleScope.launch {
            try {
                cardMeList = ApiService.cardService.getUserCardMe().data.cardMeList
                cardYouList = ApiService.cardService.getUserCardYou().data.cardYouList
                initAdapter()
            } catch (e: Exception) {
                Log.d("실패", e.message.toString())
            }
        }

        cardMeAdapter = RepresentBottomSheetCardMeAdapter(requireContext())
        cardYouAdapter = RepresentBottomSheetCardYouAdapter(requireContext())

        val gridLayoutManager1 = GridLayoutManager(requireContext(), 2)
        val gridLayoutManager2 = GridLayoutManager(requireContext(), 2)

        with(binding) {
            rvRepresentcardeditCardme.layoutManager = gridLayoutManager1
            rvRepresentcardeditCardyou.layoutManager = gridLayoutManager2

            rvRepresentcardeditCardme.addItemDecoration(SpacesItemDecoration(12))
            rvRepresentcardeditCardyou.addItemDecoration(SpacesItemDecoration(12))

            rvRepresentcardeditCardme.adapter = cardMeAdapter
            rvRepresentcardeditCardyou.adapter = cardYouAdapter
        }


        cardMeAdapter.setItemClickListener { position, RepresentCardData, isSelected ->
            if (isSelected) {
                list1.add(RepresentCardData)
                cardMeAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                cardYouAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                binding.tvRepresentcardeditCardListCount.text = "${list1.size}/7"
                return@setItemClickListener list1.lastIndex
            } else {
                list1.removeAt(position)
                cardMeAdapter.setLastRemovedIndex(position)
                cardYouAdapter.setLastRemovedIndex(position)
                binding.tvRepresentcardeditCardListCount.text = "${list1.size}/7"
                cardYouAdapter.notifyDataSetChanged()

                return@setItemClickListener -1
            }
        }

        cardYouAdapter.setItemClickListener { position, RepresentCardData, isSelected ->
            if (isSelected) {
                list2.add(RepresentCardData)
                cardMeAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                cardYouAdapter.setLastRemovedIndex(Int.MAX_VALUE)
                binding.tvRepresentcardeditCardListCount.text = "${list2.size}/7"

                return@setItemClickListener list2.lastIndex
            } else {
                list2.removeAt(position)
                cardMeAdapter.setLastRemovedIndex(position)
                cardYouAdapter.setLastRemovedIndex(position)
                binding.tvRepresentcardeditCardListCount.text = "${list2.size}/7"
                cardMeAdapter.notifyDataSetChanged()

                return@setItemClickListener -1
            }
        }

        onResultClick()
    }

    private fun initAdapter() {
        cardMeAdapter.cardMeList.addAll(cardMeList)
        cardYouAdapter.cardYouList.addAll(cardYouList)

        cardMeAdapter.notifyDataSetChanged()
        cardYouAdapter.notifyDataSetChanged()
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