package com.myappventure.app.data.remote.komunitas.detail_postingan_komunitas


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.myappventure.app.data.remote.getAllPostingan.FilePost
import com.myappventure.app.data.remote.komunitas.get_postingan_komunitas.KomentarBy
import com.myappventure.app.data.remote.komunitas.get_postingan_komunitas.LikedBy
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Data(
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
    @SerializedName("komentarBy")
    val komentarBy: List<KomentarBy>,
    @SerializedName("likedBy")
    val likedBy: List<LikedBy>,
//    @SerializedName("postIn")
//    val postIn: PostIn,
    @SerializedName("text")
    val text: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName1")
    val urlFileName1: String,
    @SerializedName("user")
    val user: User
) : Parcelable