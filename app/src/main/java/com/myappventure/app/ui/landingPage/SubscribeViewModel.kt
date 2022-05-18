package com.myappventure.app.ui.landingPage

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.subscribe.SubscribeResponse
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SubscribeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    val subscribeResponse = MutableLiveData<SubscribeResponse>()

    suspend fun subscribe(email: String) {
        authRepository.subscribe(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            statusCode = {
                _statusCode.postValue(it)
            },
            email
        ).collect {
            subscribeResponse.postValue(it)
        }
    }
}