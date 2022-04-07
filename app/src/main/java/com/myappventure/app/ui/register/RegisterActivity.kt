package com.myappventure.app.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.txtMasuk.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    override fun setupObserver() {
        viewModel.message.observe(this) {
            //TODO SHOW LINEAR LAYOUT DAN UBAH TEXTVIEW
        }
        viewModel.loading.observe(this) {
            if (it) showLoading() else hideLoading()
        }
    }

    private fun register() {
        val email = binding.edtEmail.text.toString()
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()

        if (username.isEmpty()) {
            binding.edtUsername.error = "Username harus diisi"
        }
        if (email.isEmpty()) {
            binding.edtEmail.error = "Email harus diisi"
        }
        if (password.isEmpty()) {
            binding.edtPassword.error = "Username harus diisi"
        } else {
            lifecycleScope.launch {
                viewModel.startRegister(email, username, password)
            }
        }
    }
}