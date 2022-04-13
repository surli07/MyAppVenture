package com.myappventure.app.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.dialog.CustomLoadingDialog
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
//    val passwordREGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{6,}$")

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
            if (it) loadingUi.show() else loadingUi.dismiss()
        }
        viewModel.message.observe(this) {
            showMessageToast(it)
        }
        val i = Intent(this, SuksesRegisterActivity::class.java)
        startActivity(i)
        finish()

    }

    private fun register() {
        val email = binding.edtEmail.text.toString()
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()

        if (username.isEmpty()) {
            binding.edtUsername.error = "Username Harus Diisi"
        } else if (username.length < 4) {
            binding.edtUsername.error = "Username Tidak Boleh Kurang Dari 3 Karakter"
        } else if (email.isEmpty()) {
            binding.edtEmail.error = "Email Harus Diisi"
        } else if (!emailPattern.matcher(email).matches()) {
            binding.edtEmail.error = "Email Tidak Valid"
        } else if (password.isEmpty()) {
            binding.edtPassword.error = "Password Harus Diisi"
        } else if (password.length < 7) {
            binding.edtPassword.error = "Password Tidak Boleh Kurang Dari 6 Karakter"
        } else {
            lifecycleScope.launch {
                viewModel.startRegister(email, username, password)
                setupObserver()
            }
        }
    }
}