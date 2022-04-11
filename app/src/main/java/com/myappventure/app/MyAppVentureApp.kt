package com.myappventure.app

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.myappventure.app.data.local.MySharedPref
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyAppVentureApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupHawk()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupHawk() {
        MySharedPref.appInit(this)
    }

}