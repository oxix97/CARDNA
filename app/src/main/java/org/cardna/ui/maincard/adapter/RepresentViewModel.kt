package org.cardna.ui.maincard.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.cardna.data.remote.model.representcardedit.RepresentCardMeData

class RepresentViewModel : ViewModel() {
    var oldList = MutableLiveData<ArrayList<RepresentCardMeData>>()
    var newList = arrayListOf<RepresentCardMeData>()

    fun add(data: RepresentCardMeData) {
        newList.add(data)
        oldList.value = newList
    }

    fun addAll(list: List<RepresentCardMeData>) {
        newList.addAll(list)
        oldList.value = newList
    }

    fun remove(data: RepresentCardMeData) {
        newList.remove(data)
        oldList.value = newList
    }
}
