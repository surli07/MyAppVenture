package com.myappventure.app.ui.navigation.ui.profile.komunitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myappventure.app.R
import com.myappventure.app.databinding.ActivityKomunitasBinding

class KomunitasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKomunitasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komunitas)
    }
}