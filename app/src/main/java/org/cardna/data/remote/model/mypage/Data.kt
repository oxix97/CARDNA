package org.cardna.data.remote.model.mypage


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isCard")
    val isCard: Boolean,
    @SerializedName("title")
    val title: String
)