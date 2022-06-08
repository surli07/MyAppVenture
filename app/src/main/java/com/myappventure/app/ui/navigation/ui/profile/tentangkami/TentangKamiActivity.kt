package com.myappventure.app.ui.navigation.ui.profile.tentangkami

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityTentangKamiBinding
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TentangKamiActivity : BaseActivity() {

    private lateinit var binding: ActivityTentangKamiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTentangKamiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recylerAboutUs.apply {
            this.layoutManager = GridLayoutManager(
                context, 3
            )
            this.adapter = TentangKamiAdapter(dataTentangDummy())
        }

        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finishAffinity()
        }
    }

    override fun setupObserver() {
//        TODO("Not yet implemented")
    }
}