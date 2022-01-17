package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class ResponseMainCardData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val isMyCard: Boolean,
        val isFriend: Boolean,
        val mainCardList: List<MainCard>
    ){
        data class MainCard(
            val id: Int,
            val mainOrder: Int,
            val isMe: Boolean,
            val cardImg: String,
            val title: String
        )
    }
}