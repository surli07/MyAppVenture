package com.myappventure.app.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivitySuksesRegisterBinding
import com.myappventure.app.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuksesRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuksesRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuksesRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kotakSilang.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}