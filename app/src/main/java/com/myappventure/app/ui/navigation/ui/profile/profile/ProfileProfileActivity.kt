package com.myappventure.app.ui.navigation.ui.profile.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivityProfileProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.username.addTextChangedListener {
            validateUpdateForm()
        }
        binding.kataSandi.addTextChangedListener {
            validateUpdateForm()
        }

        binding.imgKembali.setOnClickListener {
            finish()
        }
    }

    private fun validateUpdateForm() {
        val username = binding.username.text.toString().trim()
        val password = binding.kataSandi.text.toString().trim()
        when {
            username.isEmpty() -> {
                binding.btnSimpan.isEnabled = false
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            password.isEmpty() -> {
                binding.btnSimpan.isEnabled = true
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else -> {
                binding.btnSimpan.isEnabled = true
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
        when {
            username.isEmpty() -> {
                binding.btnSimpan.isEnabled = true
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            password.isEmpty() -> {
                binding.btnSimpan.isEnabled = false
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else -> {
                binding.btnSimpan.isEnabled = true
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
        when {
            username.isEmpty() -> {
                binding.btnSimpan.isEnabled = false
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            password.isEmpty() -> {
                binding.btnSimpan.isEnabled = false
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else -> {
                binding.btnSimpan.isEnabled = true
                binding.btnSimpan.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
    }
}