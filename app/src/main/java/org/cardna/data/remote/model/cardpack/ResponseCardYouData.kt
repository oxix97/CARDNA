package org.cardna.data.remote.model.cardpack

data class ResponseCardYouData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: CardList
) {

    data class CardList(
        val totalCardCnt: Int,
        val isMyCard: Boolean,
        val cardYouList: MutableList<CardYouList>
    )
}

data class CardYouList(
    val id: Int,
    val cardImg: String,
    val title: String,
    val mainOrder: Int,
    val isLiked: Boolean
)
