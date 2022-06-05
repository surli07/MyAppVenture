package com.myappventure.app.ui.navigation.ui.profile.profile

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.edit_profile.EditProfileResponse
import com.myappventure.app.repository.EditProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProfileProfileViewmodel @Inject constructor(
    private val editProfileRepository: EditProfileRepository
) : BaseViewModel() {

    val editProfileResult = MutableLiveData<EditProfileResponse>()
    val idUser = MySharedPref.idUser.toString()
    val username = MySharedPref.userName.toString()

    suspend fun editProfile() {
        editProfileRepository.editProfile(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            password = String(),
        ).collect {
            editProfileResult.postValue(it)
        }
    }
}