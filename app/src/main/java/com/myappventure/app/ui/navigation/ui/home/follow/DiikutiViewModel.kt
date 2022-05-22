package com.myappventure.app.ui.navigation.ui.home.follow

import com.myappventure.app.base.BaseViewModel
import com.myappventure.app.repository.PostinganRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiikutiViewModel @Inject constructor(
    private val postinganRepository: PostinganRepository
) : BaseViewModel() {

}