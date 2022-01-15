package org.cardna.data.remote.api

data class RepresentCardData(
    val image: Int,
    val userTag: String,
    val backgroundColor: Int,
    var isClicked: Boolean = false,
    var index : Int = -1
)
