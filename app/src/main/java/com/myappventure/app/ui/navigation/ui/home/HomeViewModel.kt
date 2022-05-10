package com.myappventure.app.ui.navigation.ui.home

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.getAllPostingan.Data
import com.myappventure.app.repository.PostinganRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val postinganRepository: PostinganRepository
) : BaseViewModel() {

    val postinganResult = MutableLiveData<Data>()

    suspend fun getAllPost() {
        postinganRepository.getAllPostingan(
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
            postinganResult.postValue(it.data)
        }
    }
}