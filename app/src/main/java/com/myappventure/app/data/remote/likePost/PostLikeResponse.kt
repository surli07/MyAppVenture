package com.myappventure.app.data.remote.likePost


import com.google.gson.annotations.SerializedName

data class PostLikeResponse(
    @SerializedName("data")
    val `data`: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)