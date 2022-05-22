package com.myappventure.app.ui.navigation.ui.profile.unggahan
import android.os.Bundle
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityUnggahanSayaBinding

class UnggahanSayaActivity : BaseActivity() {
    private lateinit var binding: ActivityUnggahanSayaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnggahanSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgKembali.setOnClickListener {
            finish()
        }
    }

    override fun setupObserver() {
//        TODO("Not yet implemented")
    }
}