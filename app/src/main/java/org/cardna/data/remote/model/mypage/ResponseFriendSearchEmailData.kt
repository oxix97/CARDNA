package org.cardna.data.remote.model.mypage


import com.google.gson.annotations.SerializedName

data class ResponseFriendSearchEmailData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
){
    data class Data(
        val email: String,
        val id: Int,
        val isFriend: Boolean,
        val name: String,
        val userImg: String
    )
}