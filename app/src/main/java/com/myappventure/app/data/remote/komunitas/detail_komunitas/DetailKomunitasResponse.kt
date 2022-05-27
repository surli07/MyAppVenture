package com.myappventure.app.data.remote.komunitas.detail_komunitas


import com.google.gson.annotations.SerializedName

data class DetailKomunitasResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)