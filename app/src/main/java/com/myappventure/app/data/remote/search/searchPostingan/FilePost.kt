package com.myappventure.app.data.remote.search.searchPostingan


import com.google.gson.annotations.SerializedName

data class FilePost(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("url")
    val url: String
)