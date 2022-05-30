package com.myappventure.app.data.remote.komunitas.get_postingan_komunitas


import com.google.gson.annotations.SerializedName

data class GetPostinganKomunitasResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)