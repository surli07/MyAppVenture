package com.myappventure.app.data.remote.followerFollowing.jumlahFollowing


import com.google.gson.annotations.SerializedName

data class JumlahFollowingResponse(
    @SerializedName("Data")
    val `data`: Int,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: String
)