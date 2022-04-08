package com.myappventure.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityLoginBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.register.RegisterActivity
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
        binding.txtDaftarDisini.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun setupObserver() {
        viewModel.message.observe(this) {
            //TODO SHOW LINEAR LAYOUT DAN UBAH TEXTVIEW
            binding.linearPeringatan.visibility = View.VISIBLE
        }
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.hide()
        }
    }
}