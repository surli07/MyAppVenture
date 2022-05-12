package com.myappventure.app.data.remote.destinasi.AllListDestinasi


import com.google.gson.annotations.SerializedName

data class AllDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)