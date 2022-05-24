package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
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