package com.myappventure.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.myappventure.app.databinding.ActivityOnboardingSlideBinding
import com.myappventure.app.ui.landingPage.LandingPageActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingSlideActivity : AppCompatActivity() {
    private var name = ""
    private lateinit var binding: ActivityOnboardingSlideBinding
    private val vp by lazy {
        binding.viewPager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingSlideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dotSlide = binding.dotsIndicator
        val pagerAdapter = ViewPagerOnboard(this)
        vp.adapter = pagerAdapter
        dotSlide.setViewPager2(vp)
        binding.txtLewati.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.txtLanjutkan.setOnClickListener {
            if (vp.currentItem < 3){
                vp.currentItem = vp.currentItem.plus(1)
            } else {
                val i = Intent(this, LandingPageActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}