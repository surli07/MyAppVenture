package com.myappventure.app.data.remote.getPostByFollowing


import com.google.gson.annotations.SerializedName
import java.util.*

data class Content(
    @SerializedName("created_date")
    val createdDate: Date,
    @SerializedName("deleted_date")
    val deletedDate: Any,
    @SerializedName("filePosts")
    val filePosts: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumlahKomentar")
    val jumlahKomentar: Int,
    @SerializedName("jumlahLike")
    val jumlahLike: Int,
    @SerializedName("postIn")
    val postIn: Any,
    @SerializedName("text")
    val text: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName1")
    val urlFileName1: String,
    @SerializedName("user")
    val user: User
)