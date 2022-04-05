package com.myappventure.app.data.remote.register

data class RegisterBody(
    val email: String,
    val username: String,
    val password: String,
    val fullname: String
)
