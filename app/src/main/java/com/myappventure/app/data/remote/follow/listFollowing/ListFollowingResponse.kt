package com.myappventure.app.data.remote.follow.listFollowing


import com.google.gson.annotations.SerializedName

data class ListFollowingResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)