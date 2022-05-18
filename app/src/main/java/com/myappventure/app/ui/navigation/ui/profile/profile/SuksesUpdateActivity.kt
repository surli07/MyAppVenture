package com.myappventure.app.ui.navigation.ui.profile.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.databinding.ActivitySuksesUpdateBinding
import com.myappventure.app.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuksesUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuksesUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuksesUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtOkedeh.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}