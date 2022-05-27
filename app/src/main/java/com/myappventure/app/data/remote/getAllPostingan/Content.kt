package com.myappventure.app.data.remote.getAllPostingan


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Content(
    @SerializedName("created_date")
    val createdDate: Date,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("filePosts")
    val filePosts: List<FilePost>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumlahKomentar")
    val jumlahKomentar: Int,
    @SerializedName("jumlahLike")
    val jumlahLike: Int,
    @SerializedName("postIn")
    val postIn: PostIn?,
    @SerializedName("text")
    val text: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName1")
    val urlFileName1: String,
    @SerializedName("user")
    val user: UserX
) : Parcelable