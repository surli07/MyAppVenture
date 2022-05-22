package com.myappventure.app.ui.navigation.ui.home.create_postingan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.anilokcun.uwmediapicker.UwMediaPicker
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityCreatePostinganBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class CreatePostinganActivity : BaseActivity() {
    private lateinit var binding: ActivityCreatePostinganBinding
    private val viewModel: NewPostViewModel by viewModels()
    private val File.size get() = if (!exists()) 0.0 else length().toDouble()
    private val File.sizeInKb get() = size / 1024
    private val File.sizeInMb get() = sizeInKb / 1024
    private var selectedFiles = mutableListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreatePostinganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgUpload.setOnClickListener {
            requestAccessForFile()
        }

        binding.txtPostingan.doOnTextChanged { text, _, _, _ ->
            binding.btnPost.isEnabled = text.toString().isNotEmpty()
        }

        binding.btnPost.setOnClickListener {
            createPost()
        }
        setupObserver()
    }

    private fun createPost() {
        lifecycleScope.launch {
            val text = binding.txtPostingan.text.toString()
            if (selectedFiles.isNotEmpty())
                viewModel.newPost(selectedFiles, text)
            else
                viewModel.newPost(null, text)
        }
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
            .setGridColumnCount(4)
            .setMaxSelectableMediaCount(10)
            .setLightStatusBar(true)
            .enableImageCompression(true)
            .setCompressionQuality(50)
            .setCompressedFileDestinationPath(this@CreatePostinganActivity.filesDir.path)
            .setCancelCallback {
                selectedFiles.clear()
            }
            .launch { f ->
                f?.let { files ->
                    selectedFiles.clear()
                    selectedFiles.addAll(files.map { File(it.mediaPath) })
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
        viewModel.newPostResponse.observe(this) {
            if (it.status == "200") {
                val i = Intent(this, NavigationActivity::class.java)
                startActivity(i)
                finish()
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