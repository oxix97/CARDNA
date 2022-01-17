package org.cardna.data.remote.model.mypage

import com.google.gson.annotations.SerializedName

data class ResponseFriendData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<Any>,
)