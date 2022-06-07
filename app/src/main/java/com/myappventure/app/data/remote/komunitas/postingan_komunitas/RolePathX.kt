package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RolePathX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("method")
    val method: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pattern")
    val pattern: String
) : Parcelable