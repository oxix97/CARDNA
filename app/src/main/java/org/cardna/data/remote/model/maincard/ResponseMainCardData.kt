package org.cardna.data.remote.model.maincard

data class ResponseMainCardData(
    val status: Int,
    val success: Boolean,
    val message: String,
) {
    data class Data(
        val isMyCard: Boolean,
        val isFriend: Boolean,
    ) {
        data class MainCardList(
            val id: Int,
            val mainOrder: Int,
            val isMe: String,
            val cardImg: String,
            val title: String,
        )
    }
}
