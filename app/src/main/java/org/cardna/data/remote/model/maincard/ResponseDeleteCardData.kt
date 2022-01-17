package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class ResponseDeleteCardData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
){
    data class Data(
        val id: Int,
        val isMe: Boolean,
        val title: String,
        val content: String,
    )
}