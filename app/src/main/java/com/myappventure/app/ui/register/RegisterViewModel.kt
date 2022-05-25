package com.myappventure.app.ui.register

import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.remote.register.RegisterResponse
import com.myappventure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val registerResponse = MutableLiveData<RegisterResponse>()

    suspend fun startRegister(file: File?, email: String, username: String, password: String) {
        val fullname = ""

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
        val emailPart = email.toRequestBody("text/plain".toMediaType())
        val usernamePart = username.toRequestBody("text/plain".toMediaType())
        val passwordPart = password.toRequestBody("text/plain".toMediaType())
        val fullnamePart = fullname.toRequestBody("text/plain".toMediaType())
        authRepository.registerUser(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            statusCode = {
                _statusCode.postValue(it)
            },
            fileMultiPart,
            emailPart,
            usernamePart,
            passwordPart,
            fullnamePart
        ).collect { response ->
            response.data?.let {
                registerResponse.postValue(response)
            }
            if (response.status != "200") {
                _message.postValue(response.message)
            }
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