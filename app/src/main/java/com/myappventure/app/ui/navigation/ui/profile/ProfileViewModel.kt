package com.myappventure.app.ui.navigation.ui.profile

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.followerFollowing.jumlahFollower.JumlahFollowerResponse
import com.myappventure.app.data.remote.followerFollowing.jumlahFollowing.JumlahFollowingResponse
import com.myappventure.app.data.remote.subscribe.SubscribeResponse
import com.myappventure.app.repository.AuthRepository
import com.myappventure.app.repository.FollowerFollowingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val followRepository: FollowerFollowingRepository
) : BaseViewModel() {
    val jumlahFollowerResponse = MutableLiveData<JumlahFollowerResponse>()
    val jumlahFollowingResponse = MutableLiveData<JumlahFollowingResponse>()

    suspend fun getFollower() {
        followRepository.getFollower(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            }
        ).collect {
            jumlahFollowerResponse.postValue(it)
        }
    }

    suspend fun getFollowing() {
        followRepository.getFollowing(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            }
        ).collect {
            jumlahFollowingResponse.postValue(it)
        }
    }
}