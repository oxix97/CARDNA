package org.cardna.data.remote.model.cardpack

import java.io.File

data class RequestCreateCardMeData(
    val title: String,
    val content: String,
    val symbolId: Int,
    val img: File
)