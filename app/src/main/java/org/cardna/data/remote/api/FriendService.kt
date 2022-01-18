package org.cardna.data.remote.api

import org.cardna.data.remote.model.mypage.RequestFriendUpdateData
import org.cardna.data.remote.model.mypage.ResponseFriendData
import org.cardna.data.remote.model.mypage.ResponseFriendListSearchNameData
import org.cardna.data.remote.model.mypage.ResponseFriendSearchEmailData
import org.cardna.data.remote.model.mypage.ResponseFriendUpdateData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface FriendService {
    @Headers("Content-Type:application/json")
    @GET("friend")
    fun getFriend(): Call<ResponseFriendData>

    @GET("friend/list?name={name}")
    fun getFriendName(): Call<ResponseFriendListSearchNameData>

    @GET("friend/search?.email={email}")
    fun getFriendSearchEmail(): Call<ResponseFriendSearchEmailData>

    //친구추가
    @POST("friend")
    fun postFriend(
        @Body body: RequestFriendUpdateData
    ): ResponseFriendUpdateData
}