package com.myappventure.app.data.remote.destinasi.malangDestinasi


import com.google.gson.annotations.SerializedName

data class malangDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)