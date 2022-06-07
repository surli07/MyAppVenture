package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.myappventure.app.data.remote.getAllPostingan.FilePost
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataX(
    @SerializedName("created_date")
    val createdDate: String,
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
    @SerializedName("komentarBy")
    val komentarBy: String,
    @SerializedName("likedBy")
    val likedBy: String,
    @SerializedName("postIn")
    val postIn: PostIn,
    @SerializedName("text")
    val text: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName1")
    val urlFileName1: String,
    @SerializedName("user")
    val user: User
) : Parcelable