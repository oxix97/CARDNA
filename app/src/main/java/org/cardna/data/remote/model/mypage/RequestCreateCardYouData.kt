package org.cardna.data.remote.model.mypage


import com.google.gson.annotations.SerializedName
import java.io.File

data class RequestCreateCardYouData(
    val title: String,
    val content: String,
    val relation: String,
    val symbolId: Int,
    val img: File // 이 file이 맞나 ?
)