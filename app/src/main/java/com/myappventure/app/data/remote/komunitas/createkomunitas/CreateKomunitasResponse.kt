package com.myappventure.app.data.remote.komunitas.createkomunitas


import com.google.gson.annotations.SerializedName

data class CreateKomunitasResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)