package org.cardna.data.remote.model.cardpack


data class ResponseCreateCardMeData(
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
        val createdAt: String,
        val imageUrl: String
    )
}