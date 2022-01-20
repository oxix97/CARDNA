package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class RequestMainCardEditData(
    val cards: MutableList<Int>
)