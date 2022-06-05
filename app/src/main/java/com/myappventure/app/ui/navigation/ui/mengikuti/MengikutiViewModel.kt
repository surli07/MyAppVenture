package com.myappventure.app.ui.navigation.ui.mengikuti

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.follow.listFollowing.Content
import com.myappventure.app.repository.ListFollowingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MengikutiViewModel @Inject constructor(
    private val listFollowingRepository: ListFollowingRepository
) : BaseViewModel() {

    val listFollowingResult = MutableLiveData<List<Content>>()

    suspend fun getListFollowing() {
        listFollowingRepository.getListFollowing(
            onStart = {
                _loading.postValue(true)
            },
            onComplete = {
                _loading.postValue(false)
            },
            onError = {
                _message.postValue(it)
            },
        ).collect {
            listFollowingResult.postValue(it.data.content)
        }
    }
}