package com.myappventure.app.data.remote.getAllPostingan


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoleXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rolePaths")
    val rolePaths: List<RolePathXXXXXXX>,
    @SerializedName("type")
    val type: String
) : Parcelable