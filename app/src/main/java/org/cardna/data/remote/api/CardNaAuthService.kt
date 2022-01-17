package org.cardna.data.remote.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CardNaAuthService {
    @Headers("Content-Type:application/json")
    @POST("/auth/signup/email")
    fun postSignUp(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>

    @Headers("Content-Type:application/json")
    @POST("/auth/login/email")
    fun postSignIn(
        @Body body: RequestSignInData
    ): Call<ResponseSignInData>
}