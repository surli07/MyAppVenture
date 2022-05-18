package com.myappventure.app.ui.navigation.ui.profile.pencapaian

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.databinding.ActivityPencapaianBinding

class PencapaianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPencapaianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPencapaianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}