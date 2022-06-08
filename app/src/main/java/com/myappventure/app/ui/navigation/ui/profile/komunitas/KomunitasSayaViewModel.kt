package com.myappventure.app.ui.navigation.ui.profile.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.komunitas_saya.Content
import com.myappventure.app.repository.KomunitasSayaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class KomunitasSayaViewModel @Inject constructor(
    private val komunitasSayaRepository: KomunitasSayaRepository
) : BaseViewModel() {

    val komunitasSayaResult = MutableLiveData<List<Content>>()
    val idUser = MySharedPref.idUser.toString()

    suspend fun getListKomunitas() {
        komunitasSayaRepository.getKomunitasSaya(
            onStart = {
                _loading.postValue(true)
            },
            onComplete = {
                _loading.postValue(false)
            },
            onError = {
                _message.postValue(it)
            },
            idUser
        ).collect {
            komunitasSayaResult.postValue(it.data.content)
        }
    }
}