package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class Test(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)