package com.myappventure.app.data.remote.komunitas.get_postingan_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoleXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rolePaths")
    val rolePaths: List<RolePathXXXXX>,
    @SerializedName("type")
    val type: String
) : Parcelable