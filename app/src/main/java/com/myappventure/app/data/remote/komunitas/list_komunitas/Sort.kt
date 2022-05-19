package com.myappventure.app.data.remote.komunitas.list_komunitas


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)