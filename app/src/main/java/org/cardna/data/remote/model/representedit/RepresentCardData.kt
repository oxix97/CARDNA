package org.cardna.data.remote.model.representedit

data class RepresentCardData(
    val image: Int,
    val userTag: String,
    val backgroundColor: Int,
    var isClicked: Boolean = false,
    var index : Int = -1
)
