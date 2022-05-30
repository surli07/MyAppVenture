package com.myappventure.app.ui.navigation.ui.community

import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.createkomunitas.CreateKomunitasResponse
import com.myappventure.app.repository.KomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewCommunityViewModel @Inject constructor(
    private val komunitasRepository: KomunitasRepository
) : BaseViewModel() {

    val createKomunitasResponse = MutableLiveData<CreateKomunitasResponse>()

    suspend fun startCreateKomunitas(
        file: File?,
        namaKomunitas: String,
        linkKomunitas: String,
        deskripsi: String,
    ) {
        val fileRequestBody = file?.asRequestBody(
            getMimeType(file.path)!!.toMediaType()
        )
        val fileMultiPart = if (fileRequestBody != null) {
            MultipartBody.Part.createFormData(
                "file",
                file.name,
                fileRequestBody
            )
        } else {
            null
        }
        val namakomunitasPart = namaKomunitas.toRequestBody("text/plain".toMediaType())
        val linkKomunitasPart = linkKomunitas.toRequestBody("text/plain".toMediaType())
        val deskripsiPart = deskripsi.toRequestBody("text/plain".toMediaType())
        val idUserPart = MySharedPref.idUser.toString().toRequestBody("text/plain".toMediaType())
        komunitasRepository.createKomunitas(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            fileMultiPart,
            namakomunitasPart,
            linkKomunitasPart,
            deskripsiPart,
            idUserPart
        ).collect {
            createKomunitasResponse.postValue(it)
        }
    }

    private fun getMimeType(path: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
}
