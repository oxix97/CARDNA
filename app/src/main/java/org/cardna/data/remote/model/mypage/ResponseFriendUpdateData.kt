package org.cardna.data.remote.model.mypage

import com.google.gson.annotations.SerializedName

data class ResponseFriendUpdateData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val id: Int,
        val isFriend: Boolean
    )
}