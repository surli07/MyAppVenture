package com.myappventure.app.ui.register

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.register.RegisterBody
import com.myappventure.app.data.remote.register.RegisterResponse
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private lateinit var binding: ActivityRegisterBinding
    val registerResponse = MutableLiveData<RegisterResponse>()

    suspend fun register() {
        val body = RegisterBody(
            binding.edtEmail.text.toString(),
            binding.edtUsername.text.toString(),
            binding.edtPassword.text.toString(),
            ""
        )
        authRepository.registerUser(
            onStart = {},
            onComplete = {},
            onError = {
                _message.postValue(it)
            },
            body
        ).collect {
            registerResponse.postValue(it)
        }
    }
}