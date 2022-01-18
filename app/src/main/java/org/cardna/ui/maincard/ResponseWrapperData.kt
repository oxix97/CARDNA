package org.cardna.ui.maincard

data class ResponseWrapperData<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)