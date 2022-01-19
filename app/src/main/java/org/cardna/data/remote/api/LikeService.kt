package org.cardna.data.remote.api

import org.cardna.data.remote.model.detail.RequestLikeData
import org.cardna.data.remote.model.login.ResponseLikeData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeService {
    @Headers("Content-Type:application/json")
    @POST("like")
    suspend fun postLike(
        @Body body: RequestLikeData
    ): ResponseLikeData
}