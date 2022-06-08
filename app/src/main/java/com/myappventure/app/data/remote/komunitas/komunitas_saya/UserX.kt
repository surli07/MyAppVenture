package com.myappventure.app.data.remote.komunitas.komunitas_saya


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserX(
    @SerializedName("authorities")
    val authorities: List<AuthorityX>,
    @SerializedName("fileName")
    val fileName: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("nama")
    val nama: String?,
//    @SerializedName("otp")
//    val otp: Any,
//    @SerializedName("otpExpiredDate")
//    val otpExpiredDate: String?,
    @SerializedName("roles")
    val roles: List<RoleX>,
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("username")
    val username: String
) : Parcelable