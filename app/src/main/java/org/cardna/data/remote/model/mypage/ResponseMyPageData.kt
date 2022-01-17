package org.cardna.data.remote.model.mypage

import com.google.gson.annotations.SerializedName

data class ResponseMyPageData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val email: String,
        val friendList: List<Friend>,
        val id: Int,
        val name: String,
        val userImg: String
    )
}

data class Friend(
    val id: Int,
    val name: String,
    val sentence: String,
    val userImg: String
)