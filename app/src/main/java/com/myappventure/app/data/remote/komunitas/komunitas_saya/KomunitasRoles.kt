package com.myappventure.app.data.remote.komunitas.komunitas_saya


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KomunitasRoles(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jenisRole")
    val jenisRole: String,
    @SerializedName("updated_date")
    val updatedDate: String
) : Parcelable