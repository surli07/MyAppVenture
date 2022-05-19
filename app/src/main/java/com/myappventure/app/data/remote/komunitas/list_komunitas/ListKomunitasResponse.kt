package com.myappventure.app.data.remote.komunitas.list_komunitas


import com.google.gson.annotations.SerializedName

data class ListKomunitasResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)