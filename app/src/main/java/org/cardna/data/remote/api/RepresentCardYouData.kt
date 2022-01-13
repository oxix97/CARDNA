package org.cardna.data.remote.api

data class RepresentCardYouData(
    val image: Int,
    val userTag: String,
    val backgroundColor: Int,
    var isClicked: Boolean = false,
)
