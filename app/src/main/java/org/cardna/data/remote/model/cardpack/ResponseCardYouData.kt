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
        val cardYouList: List<CardYou>
    ){
        data class CardYou(
            val id: Int,
            val cardImg: String,
            val title: String,
            val mainOrder: Int,
            val isLiked: Boolean
        )
    }
}