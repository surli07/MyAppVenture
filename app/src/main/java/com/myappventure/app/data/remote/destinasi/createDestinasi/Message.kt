package com.myappventure.app.data.remote.destinasi.createDestinasi


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("cause")
    val cause: Any,
    @SerializedName("localizedMessage")
    val localizedMessage: Any,
    @SerializedName("message")
    val message: Any,
    @SerializedName("stackTrace")
    val stackTrace: List<StackTrace>,
    @SerializedName("suppressed")
    val suppressed: List<Any>
)