package com.myappventure.app.data.remote.like.getLike


import com.google.gson.annotations.SerializedName

data class GetLikeResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)