package com.myappventure.app.data.remote.register


import com.google.gson.annotations.SerializedName

data class Role(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rolePaths")
    val rolePaths: List<RolePathX>,
    @SerializedName("type")
    val type: String
)