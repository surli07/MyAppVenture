package com.myappventure.app.ui.navigation.ui.profile.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.databinding.ActivityProfile2Binding
import com.myappventure.app.ui.navigation.ui.profile.ViewPagerAdapter2

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfile2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfile2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewPagerAchievement.apply {
            this.adapter = ViewPagerAdapter2(this@ProfileActivity)
        }
    }
}