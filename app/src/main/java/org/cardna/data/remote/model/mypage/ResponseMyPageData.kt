package org.cardna.data.remote.model.mypage

import com.google.gson.annotations.SerializedName

data class ResponseMyPageData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val id: Int,
        val name: String,
        val userImg: String,
        val email: String,
        val friendList: List<ResponseMyPageFriendData>,
    )
}

data class ResponseMyPageFriendData(
    val id: Int,
    val name: String,
    val userImg: String,
    val sentence: String,
)