package org.cardna.data.remote.model.representcardedit

data class RepresentCardYouBottomSheetData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val cardYouList: MutableList<UpdateData>,
        val isMyCard: Boolean,
        val totalCardCnt: Int
    )
}