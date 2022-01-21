package org.cardna.data.remote.api

import org.cardna.data.remote.model.detail.RequestLikeData
import org.cardna.data.remote.model.login.ResponseLikeData
import org.cardna.data.remote.model.mypage.RequestFriendUpdateData
import org.cardna.data.remote.model.mypage.ResponseFriendData
import org.cardna.data.remote.model.mypage.ResponseFriendListSearchNameData
import org.cardna.data.remote.model.mypage.ResponseFriendSearchEmailData
import org.cardna.data.remote.model.mypage.ResponseFriendUpdateData
import retrofit2.Call
import retrofit2.http.*

interface FriendService {
    @Headers("Content-Type:application/json")
    @GET("friend")
    fun getFriend(): Call<ResponseFriendData>

    //친구 이름으로 검색@Query("company_name")
    @GET("friend/list?name")
    suspend fun getFriendName(
        @Query("name")
        name: String?
    ): ResponseFriendListSearchNameData

    //친구 이메일로 검색
    @GET("friend/search?.email")
    suspend fun getFriendSearchEmail(
        @Query("email")
        email: String?
    ): ResponseFriendSearchEmailData

    //친구추가
    @POST("friend")
    suspend fun postFriend(
        @Body body: RequestFriendUpdateData
    ): ResponseFriendUpdateData
}

