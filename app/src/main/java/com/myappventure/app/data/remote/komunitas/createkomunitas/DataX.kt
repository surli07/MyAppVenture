package com.myappventure.app.data.remote.komunitas.createkomunitas


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("accept")
    val accept: Boolean,
    @SerializedName("banner")
    val banner: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: Any,
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
)