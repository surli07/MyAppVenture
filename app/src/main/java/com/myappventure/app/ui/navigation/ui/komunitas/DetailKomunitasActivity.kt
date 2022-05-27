package com.myappventure.app.ui.navigation.ui.komunitas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.detail_komunitas.Data
import com.myappventure.app.databinding.ActivityDetailKomunitasBinding
import com.myappventure.app.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailKomunitasActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailKomunitasBinding
    private val postinganKomunitasAdapter = PostinganKomunitasAdapter(mutableListOf(), onClick = {
        if (!MySharedPref.isLoggedIn) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    })
    private lateinit var detailPost: Data
    private val detailKomunitasAdapter = DetailKomunitasAdapter(mutableListOf())
    private val postinganKomunitasViewModel: PostinganKomunitasViewModel by viewModels()
    private val detaiKomunitasViewModel: DetailKomunitasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKomunitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUnggah.setOnClickListener {
            val i = Intent(this, PostinganKomunitasActivity::class.java)
            startActivity(i)
        }
        binding.btnKembali.setOnClickListener {
            finish()
        }
        intent.getParcelableExtra<Data>("komunitas")?.let {
            detailPost = it
            binding.txtDeskripsiKomunitas.text = it.deskripsi
            binding.txtNamaKomunitas.text = it.namaKomunitas
            binding.txtLinkGrup.text = it.linkKomunitas
            Glide.with(binding.imgFoto.context)
                .load(it.urlFileName)
                .into(binding.imgFoto)
        }
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

    override fun setupObserver() {
        detaiKomunitasViewModel.detailKomunitasResult.observe(this) {
            detailKomunitasAdapter.komunitas.clear()
            detailKomunitasAdapter.komunitas.add(it)
        }
        postinganKomunitasViewModel.postinganKomunitasResult.observe(this) {
            postinganKomunitasAdapter.postingan.clear()
            postinganKomunitasAdapter.notifyDataSetChanged()
        }
    }
}