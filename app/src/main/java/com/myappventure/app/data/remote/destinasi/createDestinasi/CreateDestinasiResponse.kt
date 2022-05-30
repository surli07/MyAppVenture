package com.myappventure.app.data.remote.destinasi.createDestinasi


import com.google.gson.annotations.SerializedName

data class CreateDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)