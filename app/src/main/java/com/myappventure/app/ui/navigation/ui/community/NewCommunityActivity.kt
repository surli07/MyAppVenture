package com.myappventure.app.ui.navigation.ui.community

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.anilokcun.uwmediapicker.UwMediaPicker
import com.bumptech.glide.Glide
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityNewCommunityBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class NewCommunityActivity : BaseActivity() {

    private lateinit var binding: ActivityNewCommunityBinding
    private val viewModel: NewCommunityViewModel by viewModels()

    var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardPenUpload.setOnClickListener {
            requestAccessForFile()
        }
        binding.imgProfile.setOnClickListener {
            requestAccessForFile()
        }
        binding.btnBuatKomunitas.setOnClickListener {
            newKomunitas()
        }
        binding.imgKembali.setOnClickListener {
            finish()
        }
        setupObserver()
    }

    private fun requestAccessForFile() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                777
            )
        } else {
            selectFileForUpload()
        }
    }

    private fun selectFileForUpload() {
        UwMediaPicker
            .with(this)
            .setGalleryMode(UwMediaPicker.GalleryMode.ImageGallery)
            .setGridColumnCount(3)
            .setMaxSelectableMediaCount(1)
            .setLightStatusBar(true)
            .enableImageCompression(true)
            .setCompressionQuality(50)
            .setCompressedFileDestinationPath(filesDir.path)
            .launch { files ->
                if (files != null) {
                    file = File(files[0].mediaPath)
                    Glide.with(this).load(files[0].mediaPath).into(binding.imgFoto)
                }
            }
    }

    override fun setupObserver() {
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.dismiss()
        }
        viewModel.message.observe(this) {
            showMessageToast(it)
        }
        viewModel.createKomunitasResponse.observe(this) {
            val i = Intent(this@NewCommunityActivity, SuksesNewCommunityActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    private fun newKomunitas() {
        val namakomunitas = binding.namaKomunitas.text.toString()
        val link = binding.linkGrup.text.toString()
        val deskripsi = binding.Deskripsi.text.toString()

        lifecycleScope.launch {
            if (file != null) {
                viewModel.startCreateKomunitas(file!!, namakomunitas, link, deskripsi)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 777) {
            if (
                grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                selectFileForUpload()
            } else {
                Toast.makeText(
                    this,
                    "The app needs your permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}