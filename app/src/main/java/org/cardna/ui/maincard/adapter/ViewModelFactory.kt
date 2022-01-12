package org.cardna.ui.maincard.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepresentViewModel::class.java)) {
            return RepresentViewModel() as T
        }
        throw IllegalArgumentException("ViewModel error")
    }
}