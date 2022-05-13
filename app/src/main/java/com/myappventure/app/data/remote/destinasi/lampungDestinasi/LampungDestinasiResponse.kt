package com.myappventure.app.data.remote.destinasi.lampungDestinasi


import com.google.gson.annotations.SerializedName

data class LampungDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)