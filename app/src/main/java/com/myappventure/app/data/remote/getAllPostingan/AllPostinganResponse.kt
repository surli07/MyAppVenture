package com.myappventure.app.data.remote.getAllPostingan


import com.google.gson.annotations.SerializedName

data class AllPostinganResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)