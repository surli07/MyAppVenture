package com.myappventure.app.ui.landingPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivitySuccessLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessLandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_landing)
    }
}