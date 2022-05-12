package com.myappventure.app.data.remote.destinasi.baliDestinasi


import com.google.gson.annotations.SerializedName

data class BaliDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)