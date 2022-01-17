package org.cardna.data.remote.model.cardpack


data class ResponseCardDetailData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
){
    data class Data(
        val id: String,
        val isMe: Boolean,
        val isCard: Boolean,
        val cardImg: String, // multipart form data ?
        val title: String,
        val content: String,
        val relation: String,
        val name: String,
        val createdAt: String,
        val isLiked: Boolean
        )
}