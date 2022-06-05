package com.myappventure.app.ui.navigation.ui.follow

import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.repository.FollowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class FollowViewmodel @Inject constructor(
    private val followRepository: FollowRepository
) : BaseViewModel() {

    val id = MySharedPref.idUser.toString()
    val idFollower = id.toRequestBody("text/plain".toMediaType())
    val idFollowing = id.toRequestBody("text/plain".toMediaType())

    suspend fun followUser() {
        followRepository.followUser(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            idFollower,
            idFollowing
        )
    }
}