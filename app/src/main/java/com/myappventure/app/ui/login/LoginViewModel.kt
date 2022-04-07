package com.myappventure.app.ui.login

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.login.LoginBody
import com.myappventure.app.data.remote.login.LoginResponse
import com.myappventure.app.databinding.ActivityLoginBinding
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private lateinit var binding: ActivityLoginBinding
    val loginResponse = MutableLiveData<LoginResponse>()

    suspend fun startLogin(email: String, password: String) {
        val loginBody = LoginBody(email, password)
        authRepository.loginUser(
            onStart = {
            },
            onComplete = {
            },
            onError = {
                _message.postValue(it)
            },
            loginBody
        ).collect {
            loginResponse.postValue(it)
        }
    }
}