package com.myappventure.app.ui.navigation.ui.komunitas

import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.repository.PostinganKomunitasRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DetailKomunitasViewModel @Inject constructor(
    private val postinganKomunitasRepository: PostinganKomunitasRepository
) : BaseViewModel() {

    suspend fun postinganKomunitas() {
        postinganKomunitasRepository.postinganKomunitas(
            onStart = {
                _loading.postValue(true)
            },
            onComplete = {
                _loading.postValue(false)
            },
            onError = {
                _message.postValue(it)
            }
        ).collect {
            postinganResult.postValue(it.data.content)
        }
    }
}