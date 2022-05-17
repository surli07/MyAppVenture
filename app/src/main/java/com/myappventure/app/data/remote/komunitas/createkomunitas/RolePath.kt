package com.myappventure.app.data.remote.komunitas.createkomunitas


import com.google.gson.annotations.SerializedName

data class RolePath(
    @SerializedName("id")
    val id: Int,
    @SerializedName("method")
    val method: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pattern")
    val pattern: String
)