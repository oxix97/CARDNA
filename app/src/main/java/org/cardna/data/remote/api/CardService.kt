package org.cardna.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.cardna.data.remote.model.cardpack.ResponseCardAllData
import org.cardna.data.remote.model.cardpack.ResponseCardDetailData
import org.cardna.data.remote.model.cardpack.ResponseCardMeData
import org.cardna.data.remote.model.cardpack.ResponseCardYouData
import org.cardna.data.remote.model.cardpack.ResponseCreateCardMeData
import org.cardna.data.remote.model.maincard.RequestMainCardEditData
import org.cardna.data.remote.model.maincard.ResponseDeleteCardData
import org.cardna.data.remote.model.maincard.ResponseMainCardData
import org.cardna.data.remote.model.maincard.ResponseMainCardEditData
import org.cardna.data.remote.model.mypage.RequestCreateCardYouData
import org.cardna.data.remote.model.mypage.ResponseAddOrRemoveCardYouData
import org.cardna.data.remote.model.mypage.ResponseCardStorageData
import org.cardna.data.remote.model.mypage.ResponseCardYouBoxData
import org.cardna.data.remote.model.mypage.ResponseCreateCardYouData
import org.cardna.data.remote.model.representcardedit.RepresentCardMeBottomSheetData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouBottomSheetData
import org.cardna.data.remote.model.representcardedit.RepresentCardYouData
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

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

    //바텀싯 카드나 선택시
    @GET("card/me")
    suspend fun getBottomSheetCardMe(): RepresentCardMeBottomSheetData

    //바텀싯 카드너 선택시
    @GET("card/you")
    suspend fun getBottomSheetCardYou(): RepresentCardYouBottomSheetData


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
    // @GET("card/me")
    // suspend fun getUserCardMe(): RepresentCardMeData


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
        @PartMap body: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): ResponseCreateCardMeData


    // 카드너 작성
    @Multipart
    @POST("card/{friendId}")
    suspend fun postCreateCardYou(
        @Path("friendId") friendId: Int,
        @PartMap body:HashMap<String, RequestBody>,
        @Part image : MultipartBody.Part
    ): ResponseCreateCardYouData

    //  카드 삭제
    @DELETE("card/{cardId}")
    suspend fun deleteCard(
        @Path("cardId")
        cardId: Int?
    ): ResponseDeleteCardData

    // 카드너 보관함 조회
    @GET("card/box")
    suspend fun getCardBox(): ResponseCardStorageData

    // 카드너 추가, 보관
    @PUT("card/box/{cardId}")
    suspend fun putCardBoxCardId(
        @Path("cardId")
        cardId: Int?
    ): ResponseAddOrRemoveCardYouData
}
