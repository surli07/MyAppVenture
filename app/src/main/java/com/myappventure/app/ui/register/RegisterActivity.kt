package com.myappventure.app.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    val passwordREGEX = Pattern.compile("^" + "(?=.*[0-9])" +  "(?=.*[a-z])" +  "(?=.*[A-Z])" + "(?=.*[a-zA-Z])" +  "(?=.*[@#$%^&+=])" +  "(?=\\S+$)" +  ".{6,}" +  "$")

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
        viewModel.loading.observe(this) {
            if (it) showLoading() else hideLoading()
        }
        viewModel.message.observe(this) {
            Toast.makeText(applicationContext, "Anda berhasil registrasi", Toast.LENGTH_SHORT)
                .show()
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
        if (emailPattern.matcher(email).matches()) {
            binding.edtEmail.error = "Masukkan email dengan benar"
        }
        if (password.isEmpty() || password.length < 7 || passwordREGEX.matcher(password).matches()) {
            binding.edtPassword.error = "Minimal panjang password 6 karakter"
            
        } else {
            lifecycleScope.launch {
                viewModel.startRegister(email, username, password)
                setupObserver()
            }

        }
    }
}