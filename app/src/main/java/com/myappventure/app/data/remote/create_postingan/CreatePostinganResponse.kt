package com.myappventure.app.data.remote.create_postingan


import com.google.gson.annotations.SerializedName

data class CreatePostinganResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)