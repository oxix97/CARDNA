package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class MainCard(
    val cardImg: String,
    val id: Int,
    val isMe: Boolean,
    val mainOrder: Int,
    val title: String
)