package com.myappventure.app.data.remote.destinasi.yogyakartaDestinasi


import com.google.gson.annotations.SerializedName

data class YogyakartaDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)