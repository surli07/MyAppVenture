package com.myappventure.app.ui.navigation.ui.komunitas

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.ActivityDetailKomunitasBinding

class DetailKomunitasActivity : AppCompatActivity() {

    private var _binding: ActivityDetailKomunitasBinding? = null
    private val binding get() = _binding!!
    private val postinganKomunitasAdapter = PostinganKomunitasAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding.recyclerPostingan.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = postinganKomunitasAdapter
        }
        setupObserver()
    }

    private fun setupObserver() {
    }
}