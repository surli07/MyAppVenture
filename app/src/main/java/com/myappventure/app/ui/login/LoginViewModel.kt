package com.myappventure.app.ui.login

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.data.remote.login.LoginResponse
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val loginResponse = MutableLiveData<LoginResponse>()

    suspend fun startLogin(email: String, password: String) {
        val loginBody = LoginBody(email, password)
        authRepository.loginUser(
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
            body = loginBody
        ).collect { response ->
            response.data?.let {
                MySharedPref.isLoggedIn = true
                MySharedPref.userToken = it.accessToken
                MySharedPref.refreshToken = it.refreshToken
                MySharedPref.userEmail = it.email
//                MySharedPref.userId = it.username
                // TODO REFRESH TOKEN
                // TODO USERNAME USER
                loginResponse.postValue(response)
            }
            if (response.status != "200") {
                _message.postValue(response.message)
            }
        }
    }
}