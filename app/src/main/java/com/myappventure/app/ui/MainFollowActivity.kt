package com.myappventure.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.myappventure.app.databinding.ActivityMainFollowBinding
import com.myappventure.app.ui.navigation.ui.profile.ViewPagerAdapter2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFollowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainFollowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewPager.apply {
            this.adapter = ViewPagerAdapter2(this@MainFollowActivity)
        }
        binding.btnKembali.setOnClickListener {
            finish()
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Pengikut"
                else -> "Mengikuti"
            }
        }.attach()
    }
}