package com.myappventure.app.data.remote.komunitas.komunitas_saya


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("komunitas")
    val komunitas: Komunitas,
    @SerializedName("komunitasRoles")
    val komunitasRoles: KomunitasRoles,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user")
    val user: UserX
) : Parcelable