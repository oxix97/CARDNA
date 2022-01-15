package org.cardna.ui.cardpack

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.cardna.MainActivity
import org.cardna.R
import org.cardna.databinding.FragmentBottomDialogImageBinding
import org.cardna.databinding.FragmentBottomDialogOtherImageBinding


class BottomDialogOtherImageFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomDialogOtherImageBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다")

    var selectedImg = NONE;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomDialogOtherImageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseImageListener()

        // for Gallery
        accessGallery()
    }

    private fun accessGallery(){
        binding.btnCardcreateGallery.setOnClickListener{
            (activity as CardCreateActivity).checkPermission()
            dialog?.dismiss()
        }
    }

    private fun chooseImageListener(){
        with(binding) {
            // 5개의 심볼, 이미지 버튼에서 하나를 선택하면 나머지 선택안되도록
            // 이미지버튼 중 하나라도 selected 되어있을 때, 완료 버튼 누를 수 있게 되고, 색깔 바뀜
            // 그리고 선택을 한 클릭일 때, 완료 버튼 선택할 수 있도록 바꾸기
            imgBtnCardpackSymbol0.setOnClickListener {
                imgBtnCardpackSymbol0.isSelected = !imgBtnCardpackSymbol0.isSelected
                imgBtnCardpackSymbol1.isSelected = false
                imgBtnCardpackSymbol2.isSelected = false
                imgBtnCardpackSymbol3.isSelected = false
                imgBtnCardpackSymbol4.isSelected = false
                ifEnableCompleteBtn()
                selectedImg = SYMBOL_0
            }

            imgBtnCardpackSymbol1.setOnClickListener {
                imgBtnCardpackSymbol0.isSelected = false
                imgBtnCardpackSymbol1.isSelected = !imgBtnCardpackSymbol1.isSelected
                imgBtnCardpackSymbol2.isSelected = false
                imgBtnCardpackSymbol3.isSelected = false
                imgBtnCardpackSymbol4.isSelected = false
                ifEnableCompleteBtn()
                selectedImg = SYMBOL_1
            }

            imgBtnCardpackSymbol2.setOnClickListener {
                imgBtnCardpackSymbol0.isSelected = false
                imgBtnCardpackSymbol1.isSelected = false
                imgBtnCardpackSymbol2.isSelected = !imgBtnCardpackSymbol2.isSelected
                imgBtnCardpackSymbol3.isSelected = false
                imgBtnCardpackSymbol4.isSelected = false
                ifEnableCompleteBtn()
                selectedImg = SYMBOL_2
            }

            imgBtnCardpackSymbol3.setOnClickListener {
                imgBtnCardpackSymbol0.isSelected = false
                imgBtnCardpackSymbol1.isSelected = false
                imgBtnCardpackSymbol2.isSelected = false
                imgBtnCardpackSymbol3.isSelected = !imgBtnCardpackSymbol3.isSelected
                imgBtnCardpackSymbol4.isSelected = false
                ifEnableCompleteBtn()
                selectedImg = SYMBOL_3
            }

            imgBtnCardpackSymbol4.setOnClickListener {
                imgBtnCardpackSymbol0.isSelected = false
                imgBtnCardpackSymbol1.isSelected = false
                imgBtnCardpackSymbol2.isSelected = false
                imgBtnCardpackSymbol3.isSelected = false
                imgBtnCardpackSymbol4.isSelected = !imgBtnCardpackSymbol4.isSelected
                ifEnableCompleteBtn()
                selectedImg = SYMBOL_4
            }



            // 완료 버튼 누르면 dialog 없어지고, 현재 selected되어 있는 버튼에 대한 상수 값을 itemClick으로 넘겨준다.

            btnCardcreateComplete.setOnClickListener{
                itemClick(selectedImg)
                dialog?.dismiss()
            }



            // val imgBtnlist = listOf<ImageButton>(imgBtnCardpackSymbol0, imgBtnCardpackSymbol1, imgBtnCardpackSymbol2, imgBtnCardpackSymbol3, imgBtnCardpackSymbol4)

            // 5개의 심볼, 이미지 버튼에서 하나를 선택하면 나머지 선택안되도록
            // 선택된 이미지 버튼은 image selected 버전으로 setImageResource 바꿔주기
            // 완료버튼 누르면 현재 5개의 버튼 중 셀렉되어있는 버튼을 찾아 그에 맞는 상수를 넘겨준다


            // 갤러리부분
            /*
            clCardpackGallery.setOnClickListener {
                itemClick(GALLERY)
                dialog?.dismiss()
            }
            */
        }
    }

    private fun ifEnableCompleteBtn(){
        with(binding) {
            if (imgBtnCardpackSymbol0.isSelected || imgBtnCardpackSymbol1.isSelected || imgBtnCardpackSymbol2.isSelected ||
                imgBtnCardpackSymbol3.isSelected || imgBtnCardpackSymbol4.isSelected){
                btnCardcreateComplete.isEnabled = true
                btnCardcreateComplete.setTextColor(resources.getColor(R.color.white_1))
            }
            else {
                btnCardcreateComplete.isEnabled = false
                btnCardcreateComplete.setTextColor(resources.getColor(R.color.white_3))
            }
        }
    }

    companion object {
        const val NONE = -1
        const val SYMBOL_0 = 0
        const val SYMBOL_1 = 1
        const val SYMBOL_2 = 2
        const val SYMBOL_3 = 3
        const val SYMBOL_4 = 4
        const val GALLERY = 5
    }

}