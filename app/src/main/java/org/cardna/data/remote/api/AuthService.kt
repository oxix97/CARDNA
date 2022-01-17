package org.cardna.data.remote.api

import org.cardna.data.remote.model.detail.ResponseRefreshTokenData
import org.cardna.data.remote.model.login.RequestSignInEmailData
import org.cardna.data.remote.model.login.RequestSignUpEmailData
import org.cardna.data.remote.model.login.ResponseSignInEmailData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @Headers("Content-Type:application/json")
    @POST("/auth/signup/email")
    fun postSignUp(
        @Body body: RequestSignUpEmailData
    ): Call<RequestSignUpEmailData>

    @Headers("Content-Type:application/json")
    @POST("/auth/login/email")
    fun postSignIn(
        @Body body: RequestSignInEmailData
    ): Call<ResponseSignInEmailData>

    @GET("/auth/refreshtoken")
    fun getRefreshToken(): Call<ResponseRefreshTokenData>
}