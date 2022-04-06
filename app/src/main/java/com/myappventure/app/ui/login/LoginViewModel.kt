package com.myappventure.app.ui.login

import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    suspend fun startLogin(email: String, password: String) {
        val loginBody = LoginBody(email, password)
        authRepository.loginUser(
            onStart = {
            },
            onComplete = {
            },
            onError = {
            },
            loginBody
//TODO LIHAT DI BIN MOVIE MAIN VIEW MODEL simpan di livedata
        )
    }

}