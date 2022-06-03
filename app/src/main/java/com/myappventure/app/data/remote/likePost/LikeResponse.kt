package com.myappventure.app.data.remote.likePost


import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)