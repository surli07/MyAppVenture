package com.myappventure.app.ui.navigation.ui.destinasi

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.destinasi.detailDestinasi.Data
import com.myappventure.app.repository.DetailDestinasiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class DetailDestinasiViewModel @Inject constructor(
    private val detailDestinasiRepository: DetailDestinasiRepository
) : BaseViewModel() {

    val id = MySharedPref.idUser.toString()
    val detailDestinasiResult = MutableLiveData<Data>()
    val idDestinasi = id.toRequestBody("text/plain".toMediaType())

    suspend fun detailDestinasi() {
        detailDestinasiRepository.detailDestinasi(
            onStart = {
                _loading.postValue(true)
            },
            onComplete = {
                _loading.postValue(false)
            },
            onError = {
                _message.postValue(it)
            },
            idDestinasi
        ).collect {
            detailDestinasiResult.postValue(it.data)
        }
    }
}