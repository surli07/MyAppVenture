package com.myappventure.app.ui.navigation.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivitySearchBinding
import com.myappventure.app.ui.navigation.NavigationActivity
import com.myappventure.app.ui.navigation.ui.home.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.apply {
            this.adapter = ViewPagerSearchAdapter(this@SearchActivity)
        }

        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Komunitas"
            } else if (position == 1) {
                tab.text = "Pengguna"
            } else {
                tab.text = "Unggahan"
            }

        }.attach()
    }

}