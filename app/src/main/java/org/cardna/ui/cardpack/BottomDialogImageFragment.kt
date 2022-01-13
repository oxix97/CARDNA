package org.cardna.ui.cardpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.cardna.base.baseutil.constant
import org.cardna.databinding.FragmentBottomDialogCardBinding
import org.cardna.databinding.FragmentBottomDialogImageBinding


class BottomDialogImageFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomDialogImageBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomDialogImageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeCard()
    }

    private fun makeCard(){

    }

}