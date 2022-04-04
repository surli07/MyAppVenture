package com.myappventure.app.data.remote.register


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("authorities")
    val authorities: List<Authority>,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("otpExpiredDate")
    val otpExpiredDate: String,
    @SerializedName("roles")
    val roles: List<Role>,
    @SerializedName("username")
    val username: String
)