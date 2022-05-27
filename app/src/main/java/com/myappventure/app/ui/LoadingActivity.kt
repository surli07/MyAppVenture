package com.myappventure.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.R
import com.myappventure.app.ui.landingPage.LandingPageActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        goToLanding()
    }

    private fun goToLanding() {
        lifecycleScope.launch {
            delay(2000)
            val i = Intent(this@LoadingActivity, OnboardingActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}