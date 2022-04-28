package com.myappventure.app.data.remote.register

import java.io.File

data class RegisterBody(
    val file: File,
    val email: String,
    val username: String,
    val password: String,
    val fullname: String
)
