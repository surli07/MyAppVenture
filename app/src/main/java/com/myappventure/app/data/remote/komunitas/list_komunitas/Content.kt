package com.myappventure.app.data.remote.komunitas.list_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    @SerializedName("accept")
    val accept: Boolean,
    @SerializedName("banner")
    val banner: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumlahAnggota")
    val jumlahAnggota: String?,
    @SerializedName("linkKomunitas")
    val linkKomunitas: String,
    @SerializedName("namaKomunitas")
    val namaKomunitas: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName")
    val urlFileName: String,
//    @SerializedName("user")
//    val user: Any
): Parcelable