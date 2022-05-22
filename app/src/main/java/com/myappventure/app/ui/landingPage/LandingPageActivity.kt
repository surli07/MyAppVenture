package com.myappventure.app.ui.landingPage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityLandingPageBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@AndroidEntryPoint
class LandingPageActivity : BaseActivity() {

    private lateinit var binding: ActivityLandingPageBinding
    private val viewModel: SubscribeViewModel by viewModels()
    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.email.addTextChangedListener {
            validateEmail()
        }
        binding.kotakSilang.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.btnMasuk.setOnClickListener {
            lifecycleScope.launch {
                val email = binding.email.text.toString().trim()
                viewModel.subscribe(email)
            }
        }
        setupObserver()
    }

    private fun validateEmail() {
        val email = binding.email.text.toString().trim()
        when {
            email.isEmpty() -> {
                binding.btnMasuk.isEnabled = false
            }
            !emailPattern.matcher(email).matches() -> {

            }
            else -> {
                binding.btnMasuk.isEnabled = true
            }
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
        viewModel.subscribeResponse.observe(this) {
            val i = Intent(this@LandingPageActivity, SuccessLandingActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}