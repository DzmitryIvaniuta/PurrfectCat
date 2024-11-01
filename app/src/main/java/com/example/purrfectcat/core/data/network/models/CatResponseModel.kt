package com.example.purrfectcat.core.data.network.models

import com.google.gson.annotations.SerializedName

data class CatResponseModel(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)
