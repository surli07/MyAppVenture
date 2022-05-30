package com.myappventure.app.ui.navigation.ui.destinasi

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.destinasi.baliDestinasi.Content
import com.myappventure.app.repository.DestinasiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class BaliViewModel @Inject constructor(
    private val destinasiRepository: DestinasiRepository
) : BaseViewModel() {

    val destinasiBaliResult = MutableLiveData<List<Content>>()

    suspend fun getBaliDestinasi() {
        destinasiRepository.getBaliDestinasi(
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
            destinasiBaliResult.postValue(it.data.content)
        }
    }
}