package org.cardna.data.remote.model.representcardedit


data class RepresentCardMeData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val totalCardCnt: Int,
        val isMyCard: Boolean,
        val cardMeList: MutableList<RepresentCardMeListData>,
    )
}

data class RepresentCardMeListData(
    val id: Int,
    val title: String,
    val cardImg: String,
    val mainOrder: Int,
    val isLiked: Boolean,
)

data class RepresentCardYouData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val totalCardCnt: Int,
        val isMyCard: Boolean,
        val cardYouList: MutableList<RepresentCardYouListData>,
    )
}

data class RepresentCardYouListData(
    val id: Int,
    val title: String,
    val cardImg: String,
    val mainOrder: Int,
    val isLiked: Boolean,
)


