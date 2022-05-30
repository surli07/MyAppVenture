package com.myappventure.app.data.remote.like.getLike


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("reaction")
    val reaction: Boolean,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user")
    val user: User
)