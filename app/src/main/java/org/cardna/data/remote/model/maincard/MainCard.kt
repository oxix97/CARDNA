package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class MainCard(
    val id: Int,
    val mainOrder: Int,
    val isMe: Boolean,
    val cardImg: String,
    val title: String,
)