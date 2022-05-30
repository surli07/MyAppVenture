package com.myappventure.app.ui.navigation.ui.community

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.anilokcun.uwmediapicker.UwMediaPicker
import com.anilokcun.uwmediapicker.model.UwMediaPickerMediaType
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
    private val File.size get() = if (!exists()) 0.0 else length().toDouble()
    private val File.sizeInKb get() = size / 1024
    private val File.sizeInMb get() = sizeInKb / 1024
    private var selectedFile = mutableListOf<File>()

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
            .launch { f ->
                f?.let { files ->
                    files.forEach{
                        if(it.mediaType == UwMediaPickerMediaType.IMAGE) {
                            var gambar = File(it.mediaPath)
                            if (gambar.sizeInMb <= 10.0) {
                                selectedFile.add(File(it.mediaPath))
                                binding.imgPhotoKomunitas.visibility = View.VISIBLE
                                binding.imgFoto.visibility = View.GONE
                                Glide.with(this).load(it.mediaPath).into(binding.imgPhotoKomunitas)
                            } else {
                                Toast.makeText(
                                    this,
                                    "Maksimum foto yang dipilih harus < 20 MB",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
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
            if (selectedFile.isNotEmpty()) {
                viewModel.startCreateKomunitas(selectedFile[0], namakomunitas, link, deskripsi)
            } else {
                viewModel.startCreateKomunitas(null, namakomunitas, link, deskripsi)
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