package com.myappventure.app.data.remote.komunitas.postingan_komunitas


import com.google.gson.annotations.SerializedName

data class RolePathXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("method")
    val method: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pattern")
    val pattern: String
)