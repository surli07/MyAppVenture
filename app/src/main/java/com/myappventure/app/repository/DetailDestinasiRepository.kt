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
import okhttp3.RequestBody
import javax.inject.Inject

class DetailDestinasiRepository  @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun detailDestinasi(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        idDestinasi: RequestBody
    ) = flow {
        val response = apiService.detailDestinasi(
            idDestinasi
        )
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
}