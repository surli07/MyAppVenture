package com.myappventure.app.data.remote.komunitas.komunitas_saya


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
//    @SerializedName("authorities")
//    val authorities: List<Authority>,
    @SerializedName("fileName")
    val fileName: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("roles")
    val roles: List<Role>,
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("username")
    val username: String
) : Parcelable