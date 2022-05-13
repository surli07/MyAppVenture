package com.myappventure.app.data.remote.destinasi.makassarDestinasi


import com.google.gson.annotations.SerializedName

data class MakassarDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)