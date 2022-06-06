package com.myappventure.app.data.remote.komunitas.get_postingan_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserXX(
    @SerializedName("authorities")
    val authorities: List<AuthorityXX>,
    @SerializedName("fileName")
    val fileName: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("otp")
    val otp: String?,
    @SerializedName("otpExpiredDate")
    val otpExpiredDate: String?,
    @SerializedName("roles")
    val roles: List<RoleXX>,
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("username")
    val username: String
) : Parcelable