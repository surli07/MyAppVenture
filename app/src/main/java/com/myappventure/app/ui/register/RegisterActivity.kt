package com.myappventure.app.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivityMainBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    private fun setupObserver() {
    }
}