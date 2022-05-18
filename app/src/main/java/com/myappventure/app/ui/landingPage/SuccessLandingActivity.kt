package com.myappventure.app.ui.landingPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivitySuccessLandingBinding
import com.myappventure.app.databinding.ActivitySuksesRegisterBinding
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessLandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuccessLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtOkedeh.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}