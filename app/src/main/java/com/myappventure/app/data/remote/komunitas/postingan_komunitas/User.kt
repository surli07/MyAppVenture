package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.myappventure.app.data.remote.getAllPostingan.FilePost
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("authorities")
    val authorities: List<Authority>,
    @SerializedName("fileName")
    val fileName: List<FilePost>,
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
) : Parcelable