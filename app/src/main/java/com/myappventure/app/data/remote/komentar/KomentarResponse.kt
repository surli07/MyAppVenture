package com.myappventure.app.data.remote.komentar


import com.google.gson.annotations.SerializedName

data class KomentarResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)