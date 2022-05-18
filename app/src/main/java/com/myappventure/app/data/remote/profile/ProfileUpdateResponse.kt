package com.myappventure.app.data.remote.profile


import com.google.gson.annotations.SerializedName

data class ProfileUpdateResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)