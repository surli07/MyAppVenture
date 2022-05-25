package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.data.remote.komunitas.postingan_komunitas.DataX
import com.myappventure.app.repository.PostinganKomunitasRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DetailKomunitasViewModel @Inject constructor(
    private val postinganKomunitasRepository: PostinganKomunitasRepository
) : BaseViewModel() {

    val postinganResult = MutableLiveData<List<DataX>>()

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
            postinganResult.postValue(it.data.dataX)
        }
    }
}