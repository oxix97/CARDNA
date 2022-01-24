package org.cardna.data.remote.model.cardpack

import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

// 안쓸 수도
data class RequestCreateCardMeData(
    val title: String,
    val content: String,
    val symbolId: Int ?
) {
    fun toRequestBody() : HashMap<String, RequestBody> {// STRING 부분이 KEY값,
        return hashMapOf(
            "title" to RequestBody.create(MediaType.parse("text/plain"), title),
            "content" to RequestBody.create(MediaType.parse("text/plain"), content),
            "symbolId" to RequestBody.create(MediaType.parse("text/plain"), symbolId.toString())
        )
    }
}