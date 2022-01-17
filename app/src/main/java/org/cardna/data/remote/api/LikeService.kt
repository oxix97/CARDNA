package org.cardna.data.remote.api

import org.cardna.data.remote.model.detail.RequestLikeData
import org.cardna.data.remote.model.login.ResponseLikeData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LikeService {
    @Headers("Content-Type:application/json")
    @POST("like")
    fun postLike(
        @Body body: RequestLikeData
    ): Call<ResponseLikeData>
}