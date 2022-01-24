package org.cardna.data.remote.model.login


import com.google.gson.annotations.SerializedName

data class ResponseSignUpEmailData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
){
    data class Data(
        val accessToken: String,
        val email: String,
        val name: String,
        val refreshToken: String
    )
}