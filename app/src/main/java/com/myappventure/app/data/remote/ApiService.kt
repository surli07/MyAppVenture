package com.myappventure.app.data.remote


import com.myappventure.app.data.remote.destinasi.AllListDestinasi.AllDestinasiResponse
import com.myappventure.app.data.remote.destinasi.baliDestinasi.BaliDestinasiResponse
import com.myappventure.app.data.remote.getAllPostingan.AllPostinganResponse
import com.myappventure.app.data.remote.komunitas.createkomunitas.CreateKomunitasResponse
import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.data.remote.login.LoginResponse
import com.myappventure.app.data.remote.register.RegisterResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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

    @GET("post/list")
    suspend fun getAllPostingan(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<AllPostinganResponse>

    @GET("destinasi/list")
    suspend fun getAllDestinasi(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<AllDestinasiResponse>

    @GET("list/bali")
    suspend fun getBaliDestinasi(
        @Query("page") page: Int,
        @Query("size") size: Int,
        ): ApiResponse<BaliDestinasiResponse>

    @POST("komunitas/create")
    @Multipart
    suspend fun newKomunitas(
        @Part file: MultipartBody.Part?,
        @Part("namaKomunitas") namakomunitas: RequestBody,
        @Part("linkKomunitas") link: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("idUser") idUser: RequestBody,
    ): ApiResponse<CreateKomunitasResponse>
}