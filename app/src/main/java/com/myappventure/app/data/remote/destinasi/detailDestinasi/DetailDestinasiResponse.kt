package com.myappventure.app.data.remote.destinasi.detailDestinasi


import com.google.gson.annotations.SerializedName

data class DetailDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)