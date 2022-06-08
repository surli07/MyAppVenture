package com.myappventure.app.ui.navigation.ui.destinasi

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.data.remote.destinasi.AllListDestinasi.Content
import com.myappventure.app.databinding.ActivityDetailDestinasiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDestinasiActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailDestinasiBinding
    private lateinit var detailDestinasi: Content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDestinasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKembali.setOnClickListener {
            finish()
        }
        intent.getParcelableExtra<Content>("destinasi")?.let {
            detailDestinasi = it
            binding.txtJudulDestinasi.text = it.nama
            binding.txtDestinasi.text = it.nama
            binding.txtDetailDestinasi.text = it.tentang
            binding.txtHarga.text = it.harga
            binding.txtJam.text = it.waktu
            if (it.fileName.isNotEmpty()) {
                binding.imgFoto.visibility = View.VISIBLE
                Glide.with(binding.imgFoto.context)
                    .load(it.urlFileName)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imgFoto)
            } else {
                binding.imgFoto.visibility = View.GONE
            }
        }
        setupObserver()
    }

    override fun setupObserver() {
    }
}