package com.myappventure.app.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivityLoginBinding
import com.myappventure.app.databinding.ActivityMainBinding
import com.myappventure.app.ui.register.RegisterViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}