package com.myappventure.app.ui.navigation.ui.komunitas

import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.postingan_komunitas.PostinganKomunitasResponse
import com.myappventure.app.repository.PostinganRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostinganKomunitasViewModel @Inject constructor(
    private val postinganRepository: PostinganRepository
) : BaseViewModel() {

    val postinganKomunitasResponse = MutableLiveData<PostinganKomunitasResponse>()

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
        postinganRepository.postinganKomunitas(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            filesMultipart,
            text,
            idUser,
            id,
        ).collect {
            postinganKomunitasResponse.postValue(it)
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