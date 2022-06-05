package com.myappventure.app.ui.navigation.ui.pengikut

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.follow.listFollower.Content
import com.myappventure.app.repository.ListFollowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PengikutViewmodel @Inject constructor(
    private val listFollowerRepository: ListFollowerRepository
) : BaseViewModel() {

    val listFollowerResult = MutableLiveData<List<Content>>()

    suspend fun getListFollower() {
        listFollowerRepository.getListFollower(
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
            listFollowerResult.postValue(it.data.content)
        }
    }
}