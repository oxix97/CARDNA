package org.cardna.data.remote.model.cardpack

data class ResponseCardMeData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val totalCardCnt: Int,
        val isMyCard: Boolean,
        val cardMeList: List<Card>
    )
}

data class CardMe(
    val id: Int,
    val cardImg: String,
    val title: String,
    val mainOrder: Int,
    val isLiked: Boolean,
    var isClicked: Boolean = false,
    var index: Int = -1,
)