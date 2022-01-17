package org.cardna.data.remote.api

import org.cardna.data.remote.model.maincard.ResponseMainCardData
import retrofit2.Call
import retrofit2.http.*

interface CardService {

    //mainCard 조회
    @Headers("Content-Type:application/json")
    @GET("/card/main/{id}")
    suspend fun getCardMain(
        @Path("id") id: String
    ): ResponseMainCardData
/*
    @PUT("/card/main")
    fun putCardMain(
        @Body body:
    ): Call<>

    @GET("/card/:userId")
    fun getCardUserId(): Call<>

    @GET("/card/me/:userId")
    fun getCardMeUserId(): Call<>

    @GET("/card/you/:userId")
    fun getCardYouUserId(): Call<>

    @GET("/card/info/:cardId")
    fun getCardInfoCardId(): Call<>

    @POST("/card")
    fun postCard(
        @Body body:
    ): Call<>

    @POST("/card/:friednId")
    fun postCardFriendId(
        @Body body:
    ): Call<>

    @DELETE("/card/:cardId")
    fun deleteCardCardId(): Call<>

    @GET("/card/box")
    fun getCardBox(): Call<>

    @PUT("/card/box/:cardId")
    fun putCardBoxCardId(): Call<>*/
}