package org.cardna.ui.maincard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.FragmentRepresentCardEditBottomDialogBinding

class RepresentCardEditBottomDialogFragment :
    BaseViewUtil.BaseFragment<FragmentRepresentCardEditBottomDialogBinding>
        (R.layout.fragment_represent_card_edit_bottom_dialog) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_represent_card_edit_bottom_dialog,
            container,
            false
        )
    }

    override fun initView() {

    }

}