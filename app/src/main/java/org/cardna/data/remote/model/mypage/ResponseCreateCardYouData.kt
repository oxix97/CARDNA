package org.cardna.data.remote.model.mypage

data class ResponseCreateCardYouData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
){
    data class Data(
        val id: Int,
        val isMe: Boolean,
        val title: String,
        val content: String,
        val relation: String,
        val name: String,
        val createdAt: String,
        val imageUrl: String
    )
}