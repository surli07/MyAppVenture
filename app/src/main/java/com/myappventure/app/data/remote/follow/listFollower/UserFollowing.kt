package com.myappventure.app.data.remote.follow.listFollower


import com.google.gson.annotations.SerializedName

data class UserFollowing(
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