package com.myappventure.app.data.remote.profile


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)