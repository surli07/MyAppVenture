package com.myappventure.app.data.remote.komentar


import com.google.gson.annotations.SerializedName
import java.util.*

data class Data(
    @SerializedName("created_date")
    val createdDate: Date,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumlahBalasKomentar")
    val jumlahBalasKomentar: Int,
    @SerializedName("textKomentar")
    val textKomentar: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user")
    val user: User
)