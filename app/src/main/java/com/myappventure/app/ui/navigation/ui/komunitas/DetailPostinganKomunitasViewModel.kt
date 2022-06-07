package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.lifecycle.MutableLiveData
import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komentar.KomentarBody
import com.myappventure.app.data.remote.komentar.KomentarResponse
import com.myappventure.app.data.remote.likePost.LikeResponse
import com.myappventure.app.repository.PostinganKomunitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DetailPostinganKomunitasViewModel @Inject constructor(
    private val postinganKomunitasRepository: PostinganKomunitasRepository
) : BaseViewModel() {
    val likeResult = MutableLiveData<LikeResponse>()
    val komentarResult = MutableLiveData<KomentarResponse>()

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

    suspend fun komentarPost(idPost: Int, text: String) {
        val komentarBody = KomentarBody(text)
        val idUser = MySharedPref.idUser!!
        postinganKomunitasRepository.komentarPostingan(
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
            idUser,
            body = komentarBody
        ).collect {
            komentarResult.postValue(it)
        }
    }
}