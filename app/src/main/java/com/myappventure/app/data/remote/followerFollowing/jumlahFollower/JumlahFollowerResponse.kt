package com.myappventure.app.data.remote.followerFollowing.jumlahFollower


import com.google.gson.annotations.SerializedName

data class JumlahFollowerResponse(
    @SerializedName("Data")
    val `data`: Int,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)