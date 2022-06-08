package com.myappventure.app.ui.navigation.ui.komunitas.follow

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.join_komunitas.Data
import com.myappventure.app.repository.FollowKomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FollowKomunitasViewModel @Inject constructor(
    private val followKomunitasRepository: FollowKomunitasRepository
) : BaseViewModel() {

    val id = MySharedPref.idUser.toString()
    val joinKomunitasResult = MutableLiveData<Data>()

    suspend fun followKomunitas(idKomunitas: Int) {
        followKomunitasRepository.followKomunitas(
            onStart = {
                _loading.postValue(true)
            },
            onComplete = {
                _loading.postValue(false)
            },
            onError = {
                _message.postValue(it)
            },
            idKomunitas
        ).collect {
            joinKomunitasResult.postValue(it.data)
        }
    }
}