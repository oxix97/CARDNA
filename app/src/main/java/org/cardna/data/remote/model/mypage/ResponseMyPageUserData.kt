package org.cardna.data.remote.model.mypage

import com.google.gson.annotations.SerializedName

data class ResponseMyPageUserData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val email: String,
        val id: Int,
        val name: String,
        val userImg: String
    )
}