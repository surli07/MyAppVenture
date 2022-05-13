package com.myappventure.app.data.remote.destinasi.surabayaDestinasi


import com.google.gson.annotations.SerializedName

data class SurabayaDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)