package com.myappventure.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivityForgotPasswordBinding
import com.myappventure.app.databinding.ActivityLoginBinding
import com.myappventure.app.ui.register.RegisterActivity
import kotlinx.coroutines.launch

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            val i = Intent(this, KodeOtpActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}