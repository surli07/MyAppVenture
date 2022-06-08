package com.myappventure.app.data.remote.komunitas.komunitas_saya


import com.google.gson.annotations.SerializedName

data class KomunitasSayaResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)