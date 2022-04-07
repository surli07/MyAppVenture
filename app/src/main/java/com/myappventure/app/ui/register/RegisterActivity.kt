package com.myappventure.app.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.hide()
        }
        viewModel.message.observe(this) {
            Toast.makeText(applicationContext, "Anda berhasil registrasi", Toast.LENGTH_SHORT)
                .show()
        }
        //TODO INTENT KE LOGIN

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
                setupObserver()
            }

        }
    }
}