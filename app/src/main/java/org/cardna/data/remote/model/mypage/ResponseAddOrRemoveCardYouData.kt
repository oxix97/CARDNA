package org.cardna.data.remote.model.mypage


data class ResponseAddOrRemoveCardYouData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int,
        val isCard: Boolean,
        val title: String,
        val content: String
    )
}