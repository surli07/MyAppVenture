package com.myappventure.app.ui.profile

import android.content.Intent
import android.os.Bundle
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.FragmentMengikutiBinding
import com.myappventure.app.databinding.FragmentPengikutBinding
import com.myappventure.app.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtMengikuti.setOnClickListener {
            val i = Intent(this, FragmentMengikutiBinding::class.java)
            startActivity(i)
            finish()
        }
        binding.txtPengikut.setOnClickListener {
            val i = Intent(this, FragmentPengikutBinding::class.java)
            startActivity(i)
            finish()
        }
        setupObserver()
    }

        override fun setupObserver() {
//        TODO("Not yet implemented")
        }
    }