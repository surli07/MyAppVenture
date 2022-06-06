package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.get_postingan_komunitas.Content
import com.myappventure.app.data.remote.likePost.LikeResponse
import com.myappventure.app.repository.PostinganKomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class GetPostinganKomunitasViewModel @Inject constructor(
    private val postinganKomunitasRepository: PostinganKomunitasRepository
) : BaseViewModel() {

    val postingankomunitasResult = MutableLiveData<List<Content>>()
    val likeResult = MutableLiveData<LikeResponse>()

    suspend fun getAllPost() {
        val id = MySharedPref.idUser.toString()
        val idKomunitas = id.toRequestBody("text/plain".toMediaType())
        postinganKomunitasRepository.getPostinganKomunitas(
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
            postingankomunitasResult.postValue(it.data.content)
        }
    }

    suspend fun likePost(idPost: Int) {
        val idUser = MySharedPref.idUser!!
        postinganKomunitasRepository.likePostingan(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            idPost,
            idUser
        ).collect {
            likeResult.postValue(it)
        }
    }
}