package org.cardna.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.cardna.data.remote.model.cardpack.*
import org.cardna.data.remote.model.maincard.RequestMainCardEditData
import org.cardna.data.remote.model.maincard.ResponseDeleteCardData
import org.cardna.data.remote.model.maincard.ResponseMainCardData
import org.cardna.data.remote.model.maincard.ResponseMainCardEditData
import org.cardna.data.remote.model.mypage.RequestCreateCardYouData
import org.cardna.data.remote.model.mypage.ResponseAddOrRemoveCardYouData
import org.cardna.data.remote.model.mypage.ResponseCardYouBoxData
import org.cardna.data.remote.model.mypage.ResponseCreateCardYouData
import retrofit2.http.*

interface CardService {
    // 나의 대표 카드 조회 - userId 없이
    @GET("card/main")
    suspend fun getMainCard(): ResponseMainCardData

    // 타인의 대표카드 조회
    @GET("card/main/{userId}")
    suspend fun getOtherMainCard(
        @Path("userId")
        userId: Int?
    ): ResponseMainCardData

    // 대표 카드 수정
    @PUT("card/main")
    suspend fun putMainCardEdit(
        @Body body: RequestMainCardEditData
    ): ResponseMainCardEditData

    // 카드 전체 조회
    @GET("card/{userId}")
    suspend fun getCardAll(): ResponseCardAllData

    // 나의 대표 카드 조회
    @GET("card/me")
    suspend fun getCardMe(): ResponseCardMeData

    // 타인의 카드나 전체 조회
    @GET("card/me/{userId}")
    suspend fun getOtherCardMe(
        @Path("userId")
        userId: Int?
    ): ResponseCardMeData

    // 카드너 전체 조회
    @GET("card/you/{userId}")
    suspend fun getCardYou(
        @Path("userId")
        userId: Int?
    ): ResponseCardYouData

    // 카드 상세 조회
    @GET("card/info/{cardId}")
    suspend fun getCardDetail(): ResponseCardDetailData

    // 카드나 작성
    @Multipart
    @POST("card")
    suspend fun postCreateCardMe(
        @PartMap body:HashMap<String, RequestBody>,
        @Part image : MultipartBody.Part
    ): ResponseCreateCardMeData

    // 카드너 작성
    @POST("card/{friendId}")
    suspend fun postCreateCardYou(
        @Body body: RequestCreateCardYouData
    ): ResponseCreateCardYouData

    // 카드 삭제
    @DELETE("card/{cardId}")
    suspend fun deleteCard(): ResponseDeleteCardData

    // 카드너 보관함 조회
    @GET("card/box")
    suspend fun getCardBox(): ResponseCardYouBoxData

    // 카드너 추가, 보관
    @PUT("card/box/{cardId}")
    suspend fun putCardBoxCardId(): ResponseAddOrRemoveCardYouData
}
