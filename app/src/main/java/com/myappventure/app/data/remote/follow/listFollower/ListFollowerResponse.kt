package com.myappventure.app.data.remote.follow.listFollower


import com.google.gson.annotations.SerializedName

data class ListFollowerResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)