package com.myappventure.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityLoginBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.ForgotPasswordActivity
import com.myappventure.app.ui.navigation.NavigationActivity
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

        var email = binding.email.text.toString()
        var password = binding.password.text.toString()
        when {
            email.isEmpty() -> {
                binding.btnMasuk.isEnabled = false
                binding.btnMasuk.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))
            }
            password.isEmpty() -> {
                binding.btnMasuk.isEnabled = false
                binding.btnMasuk.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))
            }
            else -> {
                binding.btnMasuk.isEnabled = true
                binding.btnMasuk.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            }

        }
        binding.btnMasuk.setOnClickListener {
            showError(false)
            lifecycleScope.launch {
                viewModel.startLogin(email, password)
            }
        }
        binding.txtDaftarDisini.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.txtLupaKataSandi.setOnClickListener {
            val i = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
        }
        binding.imgLockPass.setOnClickListener {
            binding.password.inputType = View.AUTOFILL_TYPE_TEXT
            binding.imgLockPass.visibility = View.GONE
            binding.imgOpenPass.visibility = View.VISIBLE
        }
        binding.imgOpenPass.setOnClickListener {
            //TODO SET INPUT TYPE
            binding.imgLockPass.visibility = View.VISIBLE
            binding.imgOpenPass.visibility = View.GONE
        }

        setupObserver()
    }

    private fun showError(state: Boolean) {
        if (state) {
            binding.linearPeringatan.visibility = View.VISIBLE
        } else {
            binding.linearPeringatan.visibility = View.GONE
        }
    }

    override fun setupObserver() {
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.dismiss()
        }
        viewModel.message.observe(this) {
            showError(true)
        }
        viewModel.loginResponse.observe(this){
            if(it.accessToken == null){
                showError(true)
            } else {
                val i = Intent(this@LoginActivity, NavigationActivity::class.java)
                startActivity(i)
                finish()
            }
        }

    }
}