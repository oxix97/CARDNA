package org.cardna.data.remote.api

import org.cardna.data.remote.model.mypage.ResponseMyPageData
import org.cardna.data.remote.model.mypage.ResponseMyPageUserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MyPageService {
    @Headers("Content-Type:application/json")
    @GET("my-page")
    suspend fun getMyPage(): ResponseMyPageData

    @GET("my-page/user")
    fun getMyPageUser(): Call<ResponseMyPageUserData>
}