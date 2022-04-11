package com.myappventure.app.ui.profile

import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel(){

}