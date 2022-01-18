package org.cardna.data.remote.model.maincard


data class ResponseMainCardData(
    val data : CardList
)

data class CardList(
    val isMyCard: Boolean,
    val isFriend: Boolean,
    val mainCardList: MutableList<MainCardList>
)

data class MainCardList(
    val id: Int,
    val mainOrder: Int,
    val isMe: Boolean,
    val cardImg: String,
    val title: String
)
