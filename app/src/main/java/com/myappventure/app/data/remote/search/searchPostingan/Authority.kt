package com.myappventure.app.data.remote.search.searchPostingan


import com.google.gson.annotations.SerializedName

data class Authority(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rolePaths")
    val rolePaths: List<RolePath>,
    @SerializedName("type")
    val type: String
)