package com.myappventure.app.data.remote.getAllPostingan


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.myappventure.app.data.remote.komentar.User
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class KomentarBy(
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
) : Parcelable