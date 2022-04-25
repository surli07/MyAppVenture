package com.myappventure.app.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.w3c.dom.Text
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
    private val usernameRegex = Pattern.compile("[a-z]{3,15}")
    private val passwordREGEX = Pattern.compile("(?=\\S+$).{6,}$")

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
        binding.edtUsername.doAfterTextChanged {
            buttonEnabled()
        }
        binding.edtEmail.doAfterTextChanged {
            buttonEnabled()
        }
        binding.edtPassword.doAfterTextChanged {
            buttonEnabled()
        }
        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
        }
        binding.imgLockPass.setOnClickListener {
            binding.edtPassword.inputType = View.AUTOFILL_TYPE_TEXT
            binding.imgLockPass.visibility = View.GONE
            binding.imgOpenPass.visibility = View.VISIBLE
        }
        binding.imgOpenPass.setOnClickListener {
            binding.edtPassword.inputType = View.AUTOFILL_HINT_PASSWORD.toInt()
            binding.imgLockPass.visibility = View.VISIBLE
            binding.imgOpenPass.visibility = View.GONE
        }

        setupObserver()
    }

    override fun setupObserver() {
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.dismiss()
        }
        viewModel.message.observe(this) {
            showMessageToast(it)
        }
        viewModel.registerResponse.observe(this){
            val i = Intent(this, SuksesRegisterActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    private fun checkUsername(): Boolean {
        var username = binding.edtUsername.text.toString()
        if (!usernameRegex.matcher(username).matches()) {
            binding.txtErorUsername.visibility = View.VISIBLE
            return false
        }
        binding.txtErorUsername.visibility = View.GONE
        return true
    }

    private fun checkEmail(): Boolean {
        var email = binding.edtEmail.text.toString()
        if (!emailPattern.matcher(email).matches()) {
            binding.txtErorEmail.visibility = View.VISIBLE
            return false
        }
        binding.txtErorEmail.visibility = View.GONE
        return true
    }

    private fun checkPasssword(): Boolean {
        val password = binding.edtPassword.text.toString()
        if (!passwordREGEX.matcher(password).matches()) {
            binding.txtErorPass.visibility = View.VISIBLE
            return false
        }
        binding.txtErorPass.visibility = View.GONE
        return true
    }

    private fun buttonEnabled() {
        var username = checkUsername()
        var email = checkEmail()
        var password = checkPasssword()

        if (username && email && password) {
            binding.btnRegister.isEnabled = true
            binding.btnRegister.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        } else {
            binding.btnRegister.isEnabled = false
            binding.btnRegister.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))
        }
    }

    private fun register() {
        val email = binding.edtEmail.text.toString()
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()


        lifecycleScope.launch {
            viewModel.startRegister(email, username, password)
            setupObserver()

        }
    }
}