package com.myappventure.app.data.remote.edit_profile


import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)