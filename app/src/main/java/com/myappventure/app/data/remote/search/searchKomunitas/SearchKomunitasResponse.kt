package com.myappventure.app.data.remote.search.searchKomunitas


import com.google.gson.annotations.SerializedName

data class SearchKomunitasResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)