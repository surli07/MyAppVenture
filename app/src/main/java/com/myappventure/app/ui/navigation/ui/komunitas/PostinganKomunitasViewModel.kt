package com.myappventure.app.ui.navigation.ui.komunitas

import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.get_postingan_komunitas.Content
import com.myappventure.app.repository.PostinganKomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostinganKomunitasViewModel @Inject constructor(
    private val postinganKomunitasRepository: PostinganKomunitasRepository
) : BaseViewModel() {

    val postinganKomunitasResult = MutableLiveData<Content>()

    suspend fun newPost(files: List<File>?, text: String) {
        val id = MySharedPref.idUser.toString()
        val filesMultipart = files?.map {
            val fileRequestBody = it.asRequestBody(getMimeType(it.path)!!.toMediaType())
            return@map MultipartBody.Part.createFormData(
                "file",
                it.name, fileRequestBody
            )
        }
        val text = text.toRequestBody("text/plain".toMediaType())
        val idUser = id.toRequestBody("text/plain".toMediaType())
        val idKomunitas = id.toRequestBody("text/plain".toMediaType())
        postinganKomunitasRepository.postinganKomunitas(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            idUser,
            idKomunitas,
            filesMultipart,
            text
        )
//            .collect {
//            postinganKomunitasResult.postValue(it)
//        }
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