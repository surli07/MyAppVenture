package com.myappventure.app.repository

import com.myappventure.app.data.remote.ApiService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import javax.inject.Inject

class PostinganKomunitasRepository @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun postinganKomunitas(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        idUser: RequestBody,
        idKomunitas: RequestBody,
        file: List<MultipartBody.Part?>?,
        text: RequestBody,
    ) = flow {
        val response = apiService.postinganKomunitas(
            idUser,
            idKomunitas,
            file,
            text
        )
        response.suspendOnSuccess {
            emit(this.data)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}