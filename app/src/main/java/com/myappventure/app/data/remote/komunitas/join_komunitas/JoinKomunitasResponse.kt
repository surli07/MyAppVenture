package com.myappventure.app.data.remote.komunitas.join_komunitas


import com.google.gson.annotations.SerializedName

data class JoinKomunitasResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)