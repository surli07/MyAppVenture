package com.myappventure.app.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupObserver() {
//        TODO("Not yet implemented")
    }
}