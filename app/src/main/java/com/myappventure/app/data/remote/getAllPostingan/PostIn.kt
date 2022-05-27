package com.myappventure.app.data.remote.getAllPostingan


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostIn(
    @SerializedName("banner")
    val banner: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("fileName")
    val fileName: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumlahAnggota")
    val jumlahAnggota: Int,
    @SerializedName("linkKomunitas")
    val linkKomunitas: String,
    @SerializedName("namaKomunitas")
    val namaKomunitas: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("user")
    val user: User
) : Parcelable