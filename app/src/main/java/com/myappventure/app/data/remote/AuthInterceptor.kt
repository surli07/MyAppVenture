package com.myappventure.app.data.remote

import com.myappventure.app.data.local.MySharedPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(

) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = MySharedPref.userToken
        val builder = chain.request().newBuilder()

        if (token != null) builder.addHeader("Authorization", "Bearer $token")

        return chain.proceed(builder.build())
    }
}