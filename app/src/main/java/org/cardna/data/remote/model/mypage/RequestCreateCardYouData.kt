package org.cardna.data.remote.model.mypage


import okhttp3.MediaType
import okhttp3.RequestBody

data class RequestCreateCardYouData(
    val title: String,
    val content: String,
    val relation: String,
    val symbolId: Int?
    ){
    fun toRequestBody() : HashMap<String, RequestBody> {// STRING 부분이 KEY값,
        return hashMapOf(
            "title" to RequestBody.create(MediaType.parse("text/plain"), title),
            "content" to RequestBody.create(MediaType.parse("text/plain"), content),
            "relation" to RequestBody.create(MediaType.parse("text/plain"), relation),
            "symbolId" to RequestBody.create(MediaType.parse("text/plain"), symbolId.toString())
        )
    }
}
