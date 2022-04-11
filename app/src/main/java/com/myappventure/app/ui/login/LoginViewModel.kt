package com.myappventure.app.ui.login

import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    suspend fun startLogin(email: String, password: String, onComplete: () -> Unit) {
        val loginBody = LoginBody(email, password)
        authRepository.loginUser(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
                onComplete()
            },
            onError = {
                println(it.toString())
            },
            statusCode = {
                statusCode.postValue(it)
            },
            body = loginBody
        ).collect {
            MySharedPref.isLoggedIn = true
        }
    }
}