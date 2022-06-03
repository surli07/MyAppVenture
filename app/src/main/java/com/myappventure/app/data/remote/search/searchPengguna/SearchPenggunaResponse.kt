package com.myappventure.app.data.remote.search.searchPengguna


import com.google.gson.annotations.SerializedName

data class SearchPenggunaResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)