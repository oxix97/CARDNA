package org.cardna.ui.maincard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.data.remote.model.representcardedit.RepresentCardMeData
import org.cardna.data.remote.model.representcardedit.RepresentCardMeListData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouListData
import org.cardna.databinding.ItemRepresentCardMeBinding

class RepresentBottomSheetCardYouAdapter(private val context: Context) :
    RecyclerView.Adapter<RepresentBottomSheetCardYouAdapter.CardYouViewHolder>() {
    private var lastRemovedIndex: Int = Int.MAX_VALUE
    private var itemClickListener: ((Int, RepresentCardYouData, Boolean) -> Int)? = null
    val cardYouList = mutableListOf<RepresentCardYouListData>()

    fun setItemClickListener(listener: ((Int, RepresentCardYouData, Boolean) -> Int)) {
        itemClickListener = listener
    }

    fun setLastRemovedIndex(index: Int) {
        lastRemovedIndex = index
    }

    inner class CardYouViewHolder(private val binding: ItemRepresentCardMeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepresentCardYouListData, position: Int) {

            with(binding) {
                //보라 초록
                binding.clRvItem.setBackgroundResource(R.drawable.rectangle_left_right_main_purple)

                //이미지 삽입
                Glide.with(context).load(data.cardImg).into(ivCardpackRecyclerview)

                //카드 태그
                binding.tvCardpackRecyclerview.text = data.title

                /*      binding.tvRepresentcardCount.isVisible = data.isClicked
                      if (lastRemovedIndex < data.index) {
                          data.index = data.index - 1
                      }

                      binding.tvRepresentcardCount.text = (data.index + 1).toString()
                      binding.clRvItem.setOnClickListener {
                          data.isClicked = !data.isClicked
                          data.index = requireNotNull(itemClickListener?.invoke(data.index, data, data.isClicked))
                          notifyDataSetChanged()
                      }*/

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepresentBottomSheetCardYouAdapter.CardYouViewHolder {
        val binding =
            ItemRepresentCardMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardYouViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: RepresentBottomSheetCardYouAdapter.CardYouViewHolder,
        position: Int
    ) {
        holder.onBind(cardYouList[position], position)
    }

    override fun getItemCount(): Int = cardYouList.size
}
