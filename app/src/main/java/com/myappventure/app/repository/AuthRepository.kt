package com.myappventure.app.repository

import com.myappventure.app.data.remote.ApiService
import com.myappventure.app.data.remote.login.LoginBody
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun registerUser(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        statusCode: (code: Int) -> Unit,
        file: MultipartBody.Part?,
        email: RequestBody,
        username: RequestBody,
        password: RequestBody,
        fullname: RequestBody
    ) = flow {
        val response = apiService.registerUser(
            file,
            email,
            username,
            password,
            fullname)
        response.suspendOnSuccess {
            statusCode(this.statusCode.code)
            emit(this.data)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
            statusCode(this.statusCode.code)
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun loginUser(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        statusCode: (code: Int) -> Unit,
        body: LoginBody,
    ) = flow {
        val response = apiService.loginUser(body)
        response.suspendOnSuccess {
            statusCode(this.statusCode.code)
            emit(this.data)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
            statusCode(this.statusCode.code)
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun subscribe(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        statusCode: (code: Int) -> Unit,
        userEmail: String,
    ) = flow {
        val response = apiService.getSubscribe(userEmail)
        response.suspendOnSuccess {
            statusCode(this.statusCode.code)
            emit(this.data)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
            statusCode(this.statusCode.code)
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}