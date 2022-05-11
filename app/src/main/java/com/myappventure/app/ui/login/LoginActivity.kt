package com.myappventure.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
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

        binding.email.addTextChangedListener {
            validateLoginForm()
        }
        binding.password.addTextChangedListener {
            validateLoginForm()
        }
        binding.btnMasuk.setOnClickListener {
            lifecycleScope.launch {
                val email = binding.email.text.toString().trim()
                val password = binding.password.text.toString().trim()
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
            finish()
        }
        binding.imgLockPass.setOnClickListener {
            binding.password.transformationMethod = SingleLineTransformationMethod()
            binding.imgLockPass.visibility = View.GONE
            binding.imgOpenPass.visibility = View.VISIBLE
        }
        binding.imgOpenPass.setOnClickListener {
            binding.password.transformationMethod = PasswordTransformationMethod()
            binding.imgLockPass.visibility = View.VISIBLE
            binding.imgOpenPass.visibility = View.GONE
        }

        setupObserver()
    }

    private fun validateLoginForm() {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()
        when {
            email.isEmpty() -> {
                binding.btnMasuk.isEnabled = false
                binding.btnMasuk.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            password.isEmpty() -> {
                binding.btnMasuk.isEnabled = false
                binding.btnMasuk.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else -> {
                showError(false)
                binding.btnMasuk.isEnabled = true
                binding.btnMasuk.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
    }

    private fun showError(state: Boolean) {
        binding.txtPeringatan.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }
    override fun setupObserver() {
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.dismiss()
        }
        viewModel.message.observe(this) {
            binding.txtPeringatan.text = it
            showError(true)
        }
        viewModel.loginResponse.observe(this) {
            if (it.data?.accessToken == null) {
                showError(true)
            } else {
                val i = Intent(this@LoginActivity, NavigationActivity::class.java)
                startActivity(i)
                finishAffinity()
            }
        }

    }
}

