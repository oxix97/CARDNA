package org.cardna.data.remote.model.cardpack

data class ResponseOtherWriterData(
    val id: Int,
    val title: String,
    val relation: String,
    val name: String,
    val createdAt: String,
    val isImage: Boolean
)