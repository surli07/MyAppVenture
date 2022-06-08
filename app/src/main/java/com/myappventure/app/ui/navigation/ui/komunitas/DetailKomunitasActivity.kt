package com.myappventure.app.ui.navigation.ui.komunitas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.list_komunitas.Content
import com.myappventure.app.databinding.ActivityDetailKomunitasBinding
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.ui.komunitas.follow.FollowKomunitasViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailKomunitasActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailKomunitasBinding
    private lateinit var detailKomunitas: Content
    private val joinKomunitasViewModel: FollowKomunitasViewModel by viewModels()
    private val postinganKomunitasViewModel: GetPostinganKomunitasViewModel by viewModels()
    private val postinganKomunitasAdapter = PostinganKomunitasAdapter(mutableListOf(), onClick = {
        if (!MySharedPref.isLoggedIn) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    },
        onDetail = { postinganKomunitas ->
            val i = Intent(this, DetailPostinganKomunitasActivity::class.java)
            i.putExtra("postinganKomunitas", postinganKomunitas)
            startActivity(i)
        },
        onLike = {
            if (MySharedPref.isLoggedIn) {
                lifecycleScope.launch {
                    postinganKomunitasViewModel.likePost(it)
                }
            }

        })

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            postinganKomunitasViewModel.getAllPost(detailKomunitas.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKomunitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUnggah.setOnClickListener { idKomunitas ->
            val i = Intent(this, PostinganKomunitasActivity::class.java)
            i.putExtra("idKomunitas", detailKomunitas.id)
            startActivity(i)
        }
        binding.btnKembali.setOnClickListener {
            finish()
        }
        intent.getParcelableExtra<Content>("komunitas")?.let {
            detailKomunitas = it
            binding.txtDeskripsiKomunitas.text = it.deskripsi
            binding.txtNamaKomunitas.text = it.namaKomunitas
            binding.txtLinkGrup.text = it.linkKomunitas
            if (it.fileName.isNotEmpty()) {
                binding.imgFoto.visibility = View.GONE
                Glide.with(binding.imgFotoKomunitas.context)
                    .load(it.urlFileName)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imgFotoKomunitas)
            } else {
                binding.imgFotoKomunitas.visibility = View.GONE
            }
        }
        binding.recyclerPostingan.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = postinganKomunitasAdapter
        }
        binding.btnIkuti.setOnClickListener {
            lifecycleScope.launch {
                joinKomunitasViewModel.followKomunitas()
            }
        }
        setupObserver()
    }

    override fun setupObserver() {
        postinganKomunitasViewModel.postingankomunitasResult.observe(this) {
            postinganKomunitasAdapter.postinganKomunitas.clear()
            postinganKomunitasAdapter.postinganKomunitas.addAll(it)
        }
    }
}