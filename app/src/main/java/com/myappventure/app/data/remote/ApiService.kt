package com.myappventure.app.data.remote


import com.myappventure.app.data.remote.register.RegisterResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST
    suspend fun registerUser(
        @Query("query") query: String
    ): ApiResponse<RegisterResponse>

}