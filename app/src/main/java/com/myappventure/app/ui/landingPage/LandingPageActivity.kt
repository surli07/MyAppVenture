package com.myappventure.app.ui.landingPage

import android.content.Intent
import android.os.Bundle
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityLandingPageBinding
import com.myappventure.app.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingPageActivity : BaseActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kotakSilang.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun setupObserver() {
//        TODO("Not yet implemented")
    }
}