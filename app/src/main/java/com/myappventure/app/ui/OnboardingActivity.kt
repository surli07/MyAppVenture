package com.myappventure.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivityOnboardingBinding
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardMulai.setOnClickListener {
            val i = Intent(this, OnboardingSlideActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}