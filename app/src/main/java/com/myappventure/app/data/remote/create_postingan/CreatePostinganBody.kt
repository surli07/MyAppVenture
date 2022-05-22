package com.myappventure.app.data.remote.create_postingan

import com.google.gson.annotations.SerializedName
import java.io.File

data class CreatePostinganBody(
    @SerializedName("file")
    val file: List<File>,
    @SerializedName("text")
    val text: String,
    @SerializedName("idUser")
    val idUser: String,
)
