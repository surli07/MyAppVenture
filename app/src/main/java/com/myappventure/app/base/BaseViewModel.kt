package com.myappventure.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel()  {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    protected val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    protected val _statusCode = MutableLiveData<Int>()
    val statusCode: LiveData<Int> = _statusCode

    protected fun showLoading() {
        _loading.postValue(true)
    }

    protected fun hideLoading() {
        if (_loading.value == true) {
            _loading.postValue(false)
        }
    }

}