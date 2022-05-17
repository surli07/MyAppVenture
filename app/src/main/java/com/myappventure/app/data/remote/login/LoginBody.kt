package com.myappventure.app.data.remote.login

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("username")
    val email: String,
    @SerializedName("password")
    val password: String,
)