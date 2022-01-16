package org.cardna.ui.maincard.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.cardna.data.remote.model.representedit.RepresentCardData

class RepresentViewModel : ViewModel() {
    var oldList = MutableLiveData<ArrayList<RepresentCardData>>()
    var newList = arrayListOf<RepresentCardData>()

    fun add(data: RepresentCardData) {
        newList.add(data)
        oldList.value = newList
    }

    fun addAll(list: List<RepresentCardData>) {
        newList.addAll(list)
        oldList.value = newList
    }

    fun remove(data: RepresentCardData) {
        newList.remove(data)
        oldList.value = newList
    }
}
