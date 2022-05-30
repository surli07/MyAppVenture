package com.myappventure.app.data.remote.destinasi.createDestinasi


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("message")
    val message: Message,
    @SerializedName("status")
    val status: String
)