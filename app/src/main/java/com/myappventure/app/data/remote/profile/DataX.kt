package com.myappventure.app.data.remote.profile


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("authorities")
    val authorities: List<Authority>,
    @SerializedName("fileName")
    val fileName: String,
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
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("username")
    val username: String
)