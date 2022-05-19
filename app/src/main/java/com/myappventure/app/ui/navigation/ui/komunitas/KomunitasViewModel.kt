package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.komunitas.list_komunitas.Content
import com.myappventure.app.repository.KomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class KomunitasViewModel @Inject constructor(
    private val komunitasRepository: KomunitasRepository
) : BaseViewModel() {

    val komunitasResult = MutableLiveData<List<Content>>()

    suspend fun getListKomunitas() {
        komunitasRepository.getKomunitas(
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
            komunitasResult.postValue(it.data.content)
        }
    }
}