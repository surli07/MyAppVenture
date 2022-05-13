package com.myappventure.app.ui.navigation.ui.destinasi

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.repository.DestinasiRepository
import com.myappventure.app.repository.PostinganRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DestinasiViewModel @Inject constructor(
        private val destinasiRepository: DestinasiRepository
    ) : BaseViewModel() {

        val destinasiResult = MutableLiveData<Content>()

        suspend fun getAllDestinasi() {
            destinasiRepository.getAllDestinasi(
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
                destinasiResult.postValue(it.data.content)
            }
        }
    }