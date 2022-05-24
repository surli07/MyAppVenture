package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import com.google.gson.annotations.SerializedName

data class PostinganKomunitasResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)