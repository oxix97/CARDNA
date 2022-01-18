package org.cardna.data.remote.model.maincard


import com.google.gson.annotations.SerializedName

data class Data(
    val isFriend: Any,
    val isMyCard: Boolean,
    val mainCardList: List<MainCard>
)