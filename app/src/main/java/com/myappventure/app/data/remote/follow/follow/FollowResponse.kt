package com.myappventure.app.data.remote.follow.follow


import com.google.gson.annotations.SerializedName

data class FollowResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)