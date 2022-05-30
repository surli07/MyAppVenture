package com.myappventure.app.ui.navigation.ui.home.foryou

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.data.remote.like.postLike.PostLikeResponse
import com.myappventure.app.repository.PostinganRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PostinganViewModel @Inject constructor(
    private val postinganRepository: PostinganRepository
) : BaseViewModel() {

    val postinganResult = MutableLiveData<List<Content>>()
    val likeResult = MutableLiveData<PostLikeResponse>()

    suspend fun getAllPost() {
        postinganRepository.getAllPostingan(
            onStart = {
                _loading.postValue(true)
            },
            onComplete = {
                _loading.postValue(false)
            },
            onError = {
                _message.postValue(it)
            }
        ).collect {
            postinganResult.postValue(it.data.content)
        }
    }

    suspend fun likePost(idPost: Int) {
        val idUser = MySharedPref.idUser!!
        postinganRepository.likePostingan(
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