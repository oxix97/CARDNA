package org.cardna.data.remote.model.mypage


data class ResponseCardYouBoxData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val responseCardYouBoxDataList: MutableList<ResponseCardYouBoxDataList>
)

data class ResponseCardYouBoxDataList(
    val id: Int,
    val title: String,
    val relation: String,
    val name: String,
    val createdAt: String,
    val isImage: Boolean
)

