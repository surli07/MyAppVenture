package com.myappventure.app.ui.navigation.ui.home.create_postingan

import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.create_postingan.CreatePostinganResponse
import com.myappventure.app.repository.PostinganRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val postinganRepository: PostinganRepository
) : BaseViewModel() {

    val newPostResponse = MutableLiveData<CreatePostinganResponse>()

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
        postinganRepository.newPost(
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
            idUser
        ).collect {
            newPostResponse.postValue(it)
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