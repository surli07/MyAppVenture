package com.myappventure.app.data.remote


import com.myappventure.app.data.remote.register.RegisterBody
import com.myappventure.app.data.remote.register.RegisterResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("user/daftar")
    suspend fun registerUser(
        @Body body: RegisterBody
    ): ApiResponse<RegisterResponse>

}