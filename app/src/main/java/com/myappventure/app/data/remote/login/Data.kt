package com.myappventure.app.data.remote.login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("filename")
    val filename: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jti")
    val jti: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("username")
    val username: String
)