package com.myappventure.app.data.remote


import com.myappventure.app.data.remote.create_postingan.CreatePostinganResponse
import com.myappventure.app.data.remote.destinasi.AllListDestinasi.AllDestinasiResponse
import com.myappventure.app.data.remote.destinasi.baliDestinasi.BaliDestinasiResponse
import com.myappventure.app.data.remote.destinasi.detailDestinasi.DetailDestinasiResponse
import com.myappventure.app.data.remote.edit_profile.EditProfileResponse
import com.myappventure.app.data.remote.follow.follow.FollowResponse
import com.myappventure.app.data.remote.follow.listFollower.ListFollowerResponse
import com.myappventure.app.data.remote.follow.listFollowing.ListFollowingResponse
import com.myappventure.app.data.remote.followerFollowing.jumlahFollower.JumlahFollowerResponse
import com.myappventure.app.data.remote.followerFollowing.jumlahFollowing.JumlahFollowingResponse
import com.myappventure.app.data.remote.getAllPostingan.AllPostinganResponse
import com.myappventure.app.data.remote.getPostByFollowing.getPostByFollowingResponse
import com.myappventure.app.data.remote.komunitas.createkomunitas.CreateKomunitasResponse
import com.myappventure.app.data.remote.komunitas.detail_komunitas.DetailKomunitasResponse
import com.myappventure.app.data.remote.komunitas.join_komunitas.JoinKomunitasResponse
import com.myappventure.app.data.remote.komunitas.list_komunitas.ListKomunitasResponse
import com.myappventure.app.data.remote.komunitas.postingan_komunitas.PostinganKomunitasResponse
import com.myappventure.app.data.remote.like.postLike.PostLikeResponse
import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.data.remote.login.LoginResponse
import com.myappventure.app.data.remote.register.RegisterResponse
import com.myappventure.app.data.remote.subscribe.SubscribeResponse
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

    @POST("post/postingan/save/")
    @Multipart
    suspend fun newPost(
        @Part file: List<MultipartBody.Part>?,
        @Part("text") text: RequestBody,
        @Part("idUser") idUser: RequestBody,
    ): ApiResponse<CreatePostinganResponse>

    @GET("post/postbyfollowing")
    suspend fun getPostinganByFollowing(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("idUser") idUser: Int?,
    ): ApiResponse<getPostByFollowingResponse>

    @PUT("user-update/edit/")
    suspend fun editProfile(
        @Query("username") username: String?,
        @Query("password") password: String,
        @Query("idUser") idUser: Int?,
    ): ApiResponse<EditProfileResponse>

    @GET("destinasi/list")
    suspend fun getAllDestinasi(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<AllDestinasiResponse>

    @GET("destinasi/list/bali")
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

    @GET("subscribe/email/{user-email}")
    suspend fun getSubscribe(
        @Path("user-email") userEmail: String
    ): ApiResponse<SubscribeResponse>

    @POST("follow/")
    suspend fun followUser(
        @Query("idFollower") idFollower : RequestBody,
        @Query("idFollowing") idFollowing : RequestBody,
    ): ApiResponse<FollowResponse>

    @GET("follow/jumlahfollowing/185/{idUser}")
    suspend fun getJumlahFollowing(
        @Path("idUser") idUser: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<JumlahFollowingResponse>

    @GET("follow/jumlahfollower/3/{idUser}")
    suspend fun getJumlahFollower(
        @Path("idUser") idUser: Int?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<JumlahFollowerResponse>

    @GET("follow/follower/3")
    suspend fun getListFollower(
        @Query("idUser") idUser: Int?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<ListFollowerResponse>

    @GET("follow/following/185")
    suspend fun getListFollowing(
        @Query("idUser") idUser: Int?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<ListFollowingResponse>

    @GET("komunitas/list")
    suspend fun listKomunitas(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ApiResponse<ListKomunitasResponse>

    @POST("komunitas/join/")
    suspend fun followKomunitas(
        @Query("idUser") idUser : Int?,
        @Query("idKomunitas") idKomunitas : RequestBody,
    ): ApiResponse<JoinKomunitasResponse>

    @GET("destinasi/detaildestinasi")
    suspend fun detailDestinasi(
        @Query("idDestinasi") idDestinasi: RequestBody,
    ): ApiResponse<DetailDestinasiResponse>

    @POST("post/postingankomunitas/save")
    @Multipart
    suspend fun postinganKomunitas(
        @Part("idUser") idUser: RequestBody,
        @Part("idKomunitas") idKomunitas: RequestBody,
        @Part file: List<MultipartBody.Part?>?,
        @Part("text") text: RequestBody,
    ): ApiResponse<PostinganKomunitasResponse>

    @GET("komunitas/detailkomunitas")
    suspend fun detailKomunitas(
        @Query("idKomunitas") idKomunitas: RequestBody,
    ): ApiResponse<DetailKomunitasResponse>

    @POST("like/")
    suspend fun postLike(
        @Query("idPost") idPost: Int,
        @Query("idUser") idUser: Int,
    ): ApiResponse<PostLikeResponse>
}