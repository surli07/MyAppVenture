package com.myappventure.app.data.remote.subscribe


import com.google.gson.annotations.SerializedName

data class SubscribeResponse(
    @SerializedName("data")
    val `data`: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)