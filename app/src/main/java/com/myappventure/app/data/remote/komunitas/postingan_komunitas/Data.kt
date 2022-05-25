package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val data : List<DataX>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)