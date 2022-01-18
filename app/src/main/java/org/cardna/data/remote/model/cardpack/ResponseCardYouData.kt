package org.cardna.data.remote.model.cardpack

data class ResponseCardYouData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
){
    data class Data(
        val totalCardCnt: Int,
        val isMyCard: Boolean,
        val cardYouList: List<Card>
    )
}
data class Card(
    val id: Int,
    val cardImg: String,
    val title: String,
    val mainOrder: Int,
    val isLiked: Boolean,
    var isClicked: Boolean = false,
    var index: Int = -1,
)