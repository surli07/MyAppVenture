package com.myappventure.app.data.remote

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    fun instance(): ApiService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://myappventure-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}

fun okHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

private val logging: HttpLoggingInterceptor
    get() {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }


