package com.myappventure.app.data.remote.destinasi.jakartaDestinasi


import com.google.gson.annotations.SerializedName

data class JakartaDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)