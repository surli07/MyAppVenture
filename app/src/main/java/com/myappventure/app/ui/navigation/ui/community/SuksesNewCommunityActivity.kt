package com.myappventure.app.ui.navigation.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.databinding.ActivitySuksesNewCommunityBinding
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuksesNewCommunityActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuksesNewCommunityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuksesNewCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtOkedeh.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}