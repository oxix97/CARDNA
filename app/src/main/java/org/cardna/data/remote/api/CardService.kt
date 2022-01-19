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
import org.cardna.data.remote.model.representcardedit.RepresentCardMeData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouData
import retrofit2.http.*

interface CardService {
    //타인의 대표카드 조회
    @GET("card/main/{userId}")
    suspend fun getMainCard(
        @Path("userId")
        userId: Int?
    ): ResponseMainCardData

    //자신의의 대표드 조회
    @GET("card/main")
    suspend fun getUserMainCard(): ResponseMainCardData

    // 대표 카드 수정
    @PUT("card/main")
    suspend fun putMainCardEdit(
        @Body body: RequestMainCardEditData
    ): ResponseMainCardEditData

    // 카드 전체 조회
    @GET("card/{userId}")
    suspend fun getCardAll(): ResponseCardAllData




    // 카드팩에서
    // 나의 카드나 조회
    @GET("card/me")
    suspend fun getCardMe(): ResponseCardMeData

    // 타인의 카드나 조회
    @GET("card/me/{userId}")
    suspend fun getOtherCardMe(
        @Path("userId")
        userId: Int?
    ): ResponseCardMeData

    // maincard에서 나의 카드나 조회
    @GET("card/me")
    suspend fun getUserCardMe(): RepresentCardMeData



    // 카드팩에서
    // 나의 카드너 전체 조회
    @GET("card/you")
    suspend fun getCardYou(): ResponseCardYouData

    // 카드너 전체 조회
    @GET("card/you/{userId}")
    suspend fun getOtherCardYou(
        @Path("userId")
        userId: Int?
    ): ResponseCardYouData

    // maincard에서 나의 카드나 조회
    @GET("card/you")
    suspend fun getUserCardYou(): RepresentCardYouData


    // 타인 카드 상세 조회
    @GET("card/info/{cardId}")
    suspend fun getCardDetail(
        @Path("cardId")
        userId: Int?
    ): ResponseCardDetailData

    // 내 카드 상세 조회
    @GET("card/info")
    suspend fun getCardUserDetail(): ResponseCardDetailData

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
