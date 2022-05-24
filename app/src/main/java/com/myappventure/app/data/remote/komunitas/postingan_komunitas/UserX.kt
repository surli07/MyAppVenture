package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("authorities")
    val authorities: List<AuthorityX>,
    @SerializedName("fileName")
    val fileName: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("otpExpiredDate")
    val otpExpiredDate: String,
    @SerializedName("roles")
    val roles: List<RoleX>,
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("username")
    val username: String
)