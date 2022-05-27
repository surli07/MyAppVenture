package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.destinasi.AllListDestinasi.Content
import com.myappventure.app.data.remote.komunitas.detail_komunitas.Data
import com.myappventure.app.data.remote.komunitas.detail_komunitas.DetailKomunitasResponse
import com.myappventure.app.repository.DetailKomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class DetailKomunitasViewModel @Inject constructor(
    private val detailKomunitasRepository: DetailKomunitasRepository
) : BaseViewModel() {

    val id = MySharedPref.idUser.toString()
    val detailKomunitasResult = MutableLiveData<Data>()
    val idKomunitas = id.toRequestBody("text/plain".toMediaType())

    suspend fun detailKomunitas() {
        detailKomunitasRepository.detailKomunitas(
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
            detailKomunitasResult.postValue(it.data)
        }
    }

    suspend fun getPostinganKomunitas() {
        detailKomunitasRepository.postinganKomunitas(
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
            detailKomunitasResult.postValue(it.data)
        }
    }
}