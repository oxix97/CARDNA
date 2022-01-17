package org.cardna.data.remote.model.detail

data class ResponseDetailData(
    val cardImg: String,
    val content: String,
    val createdAt: String,
    val id: Int,
    val isCard: Boolean,
    val isLiked: Boolean,
    val isMe: Boolean,
    val name: String,
    val relation: String,
    val title: String
)