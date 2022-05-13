package com.myappventure.app.data.remote.destinasi.lombokDestinasi


import com.google.gson.annotations.SerializedName

data class LombokDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)