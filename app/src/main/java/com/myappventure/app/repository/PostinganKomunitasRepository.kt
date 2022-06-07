package com.myappventure.app.repository

import com.myappventure.app.data.remote.ApiService
import com.myappventure.app.data.remote.komentar.KomentarBody
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

    suspend fun getPostinganKomunitas(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        idKomunitas: Int,
    ) = flow {
        val response = apiService.getPostinganKomunitas(0, 100, idKomunitas)
        response.suspendOnSuccess {
            emit(this.data)
        }.onError {
            onError(this.message())
        }.onException {
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun likePostingan(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        idPost: Int,
        idUser: Int
    ) = flow {
        val response = apiService.postLike(idPost, idUser)
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            onError(this.message())
        }.onException {
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun komentarPostingan(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        idPost: Int,
        idUser: Int,
        body: KomentarBody
    ) = flow {
        val response = apiService.postKomentar(idPost, idUser, body)
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            onError(this.message())
        }.onException {
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}