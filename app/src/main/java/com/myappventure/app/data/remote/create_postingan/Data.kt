package com.myappventure.app.data.remote.create_postingan


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `data`: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)