package org.cardna.data.remote.model.representcardedit

data class RepresentCardMeBottomSheetData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
){
    data class Data(
        val cardMeList: MutableList<UpdateData>,
        val isMyCard: Boolean,
        val totalCardCnt: Int
    )
}
data class UpdateData(
    val cardImg: String,
    val id: Int,
    val isLiked: Any,
    val mainOrder: Any,
    val title: String,
    var isClicked :Boolean = false,
    var index : Int = -1,

)
