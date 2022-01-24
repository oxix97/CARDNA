package org.cardna.data.remote.model.mypage

data class ResponseCardStorageData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: MutableList<Data>,
){
    data class Data(
        val createdAt: String,
        val id: Int,
        val isImage: Boolean,
        val name: String,
        val relation: String,
        val title: String
    )
}