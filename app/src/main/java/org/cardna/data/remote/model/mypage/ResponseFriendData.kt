package org.cardna.data.remote.model.mypage

import com.google.gson.annotations.SerializedName

data class ResponseFriendData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: List<Any>,
)