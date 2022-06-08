package com.myappventure.app.ui.navigation.ui.profile.komunitas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.myappventure.app.databinding.ActivityKomunitasBinding
import com.myappventure.app.ui.navigation.ui.komunitas.DetailKomunitasActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KomunitasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKomunitasBinding
    private val komunitasSayaViewModel: KomunitasSayaViewModel by viewModels()
    private val komunitasAdapter = KomunitasSayaAdapter(mutableListOf(),
        onDetail = { komunitas ->
            val i = Intent(this, DetailKomunitasActivity::class.java)
            i.putExtra("komunitas", komunitas)
            startActivity(i)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKomunitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKembali.setOnClickListener {
            finish()
        }

        binding.recyclerCommunity.apply {
            layoutManager = GridLayoutManager(
                context,
                2
            )
            adapter = komunitasAdapter
        }
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            komunitasSayaViewModel.getListKomunitas()
        }
    }

    private fun setupObserver() {
        komunitasSayaViewModel.komunitasSayaResult.observe(this) {
            komunitasAdapter.komunitasSaya.clear()
            komunitasAdapter.komunitasSaya.addAll(it)
            komunitasAdapter.notifyDataSetChanged()
        }
    }
}