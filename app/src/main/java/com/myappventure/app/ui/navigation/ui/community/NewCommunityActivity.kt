package com.myappventure.app.ui.navigation.ui.community

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityNewCommunityBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.roundToInt
import kotlin.math.sqrt

class NewCommunityActivity : BaseActivity() {

    private lateinit var binding: ActivityNewCommunityBinding
    private val viewModel: NewCommunityViewModel by viewModels()
    private val requestGetPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedUri: Uri? = result.data?.data
            selectedUri?.let { uri ->
                binding.imgFoto.setImageURI(uri)
                binding.imgFoto.visibility = View.VISIBLE
                createFileBeforeUpload(uri)
            }
        } else {
            binding.imgFoto.visibility = View.GONE
            Toast.makeText(
                this,
                "Pemilihan gambar dibatalkan",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    var file = File("")

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
        val i = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        requestGetPhoto.launch(Intent.createChooser(i, "Select Picture"))
    }

    private fun createFileBeforeUpload(uri: Uri) {
        lifecycleScope.launch {
            kotlin.runCatching {
                try {
                    val targetOriginal =
                        BitmapFactory.decodeStream(
                            this@NewCommunityActivity.contentResolver.openInputStream(
                                uri
                            )
                        )
                    val targetCompressed = reduceBitmapSize(targetOriginal)
                    val bos = ByteArrayOutputStream()
                    targetCompressed.compress(Bitmap.CompressFormat.JPEG, 0, bos)
                    val bitmapData = bos.toByteArray()

                    file = File(
                        this@NewCommunityActivity.filesDir.path,
                        "${System.currentTimeMillis()}_compressed.jpeg"
                    )
                    file.createNewFile()
                    val fos = FileOutputStream(file)
                    fos.write(bitmapData)
                    fos.flush()
                    fos.close()

                    // lempar file ke view model untuk diupload retrofit

                } catch (e: Exception) {
                    this@NewCommunityActivity.runOnUiThread {
                        Toast.makeText(
                            this@NewCommunityActivity,
                            "Terjadi kesalahan saat menulis file",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun getFile(file: File): File {
        return file
    }

    private fun reduceBitmapSize(bitmap: Bitmap, MAX_SIZE: Int = 360000): Bitmap {
        val ratioSquare: Double
        val bitmapHeight: Int = bitmap.height
        val bitmapWidth: Int = bitmap.width
        ratioSquare = (bitmapHeight * bitmapWidth / MAX_SIZE).toDouble()
        if (ratioSquare <= 1) return bitmap
        val ratio = sqrt(ratioSquare)
        val requiredHeight = (bitmapHeight / ratio).roundToInt()
        val requiredWidth = (bitmapWidth / ratio).roundToInt()
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true)
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
            viewModel.startCreateKomunitas(file, namakomunitas, link, deskripsi)
            setupObserver()
        }
    }
}