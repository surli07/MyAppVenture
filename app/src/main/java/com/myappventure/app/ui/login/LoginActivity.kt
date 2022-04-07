package com.myappventure.app.ui.login

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMasuk.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            when {
                email.isEmpty() -> {
                    binding.email.error = "Email tidak boleh kosong"
                }
                password.isEmpty() -> {
                    binding.password.error = "Password tidak boleh kosong"
                }
                else -> {
                    lifecycleScope.launch {
                        viewModel.startLogin(email, password)
                    }
                }
            }
        }
    }

    override fun setupObserver() {
        viewModel.message.observe(this) {
            //TODO SHOW LINEAR LAYOUT DAN UBAH TEXTVIEW
            binding.linearPeringatan.visibility = View.VISIBLE
        }
        viewModel.loading.observe(this) {
            if (it) showLoading() else hideLoading()
        }
    }
}