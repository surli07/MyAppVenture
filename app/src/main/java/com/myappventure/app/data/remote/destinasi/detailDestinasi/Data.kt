package com.myappventure.app.data.remote.destinasi.detailDestinasi


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("banner")
    val banner: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("harga")
    val harga: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lokasi")
    val lokasi: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("provinsi")
    val provinsi: String,
    @SerializedName("tentang")
    val tentang: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName")
    val urlFileName: String,
    @SerializedName("waktu")
    val waktu: String
): Parcelable