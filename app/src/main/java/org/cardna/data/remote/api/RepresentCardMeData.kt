package org.cardna.data.remote.api

data class RepresentCardMeData(
    val image: Int,
    val userTag: String,
    val backgroundColor: Int,
    var isClicked: Boolean = false,
)
