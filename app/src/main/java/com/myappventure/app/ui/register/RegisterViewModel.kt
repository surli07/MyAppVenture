package com.myappventure.app.ui.register

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.register.RegisterResponse
import com.myappventure.app.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val registerResponse = MutableLiveData<RegisterResponse>()
}