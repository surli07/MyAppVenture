package com.myappventure.app.data.remote


import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.data.remote.login.LoginResponse
import com.myappventure.app.data.remote.register.RegisterBody
import com.myappventure.app.data.remote.register.RegisterResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("user/register")
    @Multipart
    suspend fun registerUser(
        @Part file: MultipartBody.Part?,
        @Part("email") email: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("fullname") fullname: RequestBody,
    ): ApiResponse<RegisterResponse>

    @POST("user-login/login")
    suspend fun loginUser(
        @Body body: LoginBody
    ): ApiResponse<LoginResponse>
}