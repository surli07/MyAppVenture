package com.myappventure.app.data.remote.komunitas.join_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("komunitasRoles")
    val komunitasRoles: KomunitasRoles,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user")
    val user: User
) : Parcelable