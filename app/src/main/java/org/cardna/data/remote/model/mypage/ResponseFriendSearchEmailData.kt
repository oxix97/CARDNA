package org.cardna.data.remote.model.mypage


import com.google.gson.annotations.SerializedName

data class ResponseFriendSearchEmailData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
){
    data class Data(
        val id: Int,
        val email: String,
        val isFriend: Boolean,
        val name: String,
        val userImg: String
    )
}