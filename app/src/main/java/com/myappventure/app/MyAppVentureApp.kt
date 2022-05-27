package com.myappventure.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
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
        lockOrientation()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupHawk() {
        MySharedPref.appInit(this)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun lockOrientation() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

        })
    }

}