package com.myappventure.app.data.remote.follow.listFollowing


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("userFollower")
    val userFollower: UserFollower,
    @SerializedName("userFollowing")
    val userFollowing: UserFollowing
) : Parcelable