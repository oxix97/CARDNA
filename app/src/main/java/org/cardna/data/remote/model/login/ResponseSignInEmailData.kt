package org.cardna.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class ResponseSignInEmailData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data,
) {
    data class Data(
        val accessToken: String,
        val email: String,
        val name: String,
        val refreshToken: String
    )
}
