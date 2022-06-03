package com.myappventure.app.ui.register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.anilokcun.uwmediapicker.UwMediaPicker
import com.anilokcun.uwmediapicker.model.UwMediaPickerMediaType
import com.bumptech.glide.Glide
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.util.regex.Pattern

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private var statusCode = 0
    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    private val File.size get() = if (!exists()) 0.0 else length().toDouble()
    private val File.sizeInKb get() = size / 1024
    private val File.sizeInMb get() = sizeInKb / 1024
    private val usernameRegex = Pattern.compile("[a-z0-9_]{3,15}")
    private val passwordREGEX = Pattern.compile("(?=\\S+$).{6,10}$")
    private var selectedFile = mutableListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardPenUpload.setOnClickListener {
            requestAccessForFile()
        }
        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.txtMasuk.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        binding.edtUsername.doAfterTextChanged {
            buttonEnabled()
        }
        binding.edtEmail.doAfterTextChanged {
            buttonEnabled()
        }
        binding.edtPassword.doAfterTextChanged {
            buttonEnabled()
        }
        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
        }
        binding.imgLockPass.setOnClickListener {
            binding.edtPassword.transformationMethod = SingleLineTransformationMethod()
            binding.imgLockPass.visibility = View.GONE
            binding.imgOpenPass.visibility = View.VISIBLE
        }
        binding.imgOpenPass.setOnClickListener {
            binding.edtPassword.transformationMethod = PasswordTransformationMethod()
            binding.imgLockPass.visibility = View.VISIBLE
            binding.imgOpenPass.visibility = View.GONE
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
            .setCancelCallback {
                selectedFile.clear()
            }
            .launch { f ->
                f?.let { files ->
                    files.forEach{
                        if(it.mediaType == UwMediaPickerMediaType.IMAGE) {
                            var gambar = File(it.mediaPath)
                            if (gambar.sizeInMb <= 10.0) {
                                selectedFile.add(File(it.mediaPath))
                                binding.imgPhotoUser.visibility = View.VISIBLE
                                binding.imgPhoto.visibility = View.GONE
                                Glide.with(this).load(it.mediaPath).into(binding.imgPhotoUser)
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

    override fun setupObserver() {
        viewModel.statusCode.observe(this) {
            statusCode = it
        }
        val loadingUi = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) loadingUi.show() else loadingUi.dismiss()
        }
        viewModel.message.observe(this) {
            showMessageToast(it)
        }
        viewModel.registerResponse.observe(this) {
            if (it.status == "200"){
                val i = Intent(this@RegisterActivity, SuksesRegisterActivity::class.java)
                startActivity(i)
                finish()
            }
        }

    }

    private fun checkUsername(): Boolean {
        var username = binding.edtUsername.text.toString()
        if (!usernameRegex.matcher(username).matches()) {
            binding.txtErorUsername.visibility = View.VISIBLE
            return false
        }
        binding.txtErorUsername.visibility = View.GONE
        return true
    }

    private fun checkEmail(): Boolean {
        var email = binding.edtEmail.text.toString()
        if (!emailPattern.matcher(email).matches()) {
            binding.txtErorEmail.visibility = View.VISIBLE
            return false
        }
        binding.txtErorEmail.visibility = View.GONE
        return true
    }

    private fun checkPasssword(): Boolean {
        val password = binding.edtPassword.text.toString()
        if (!passwordREGEX.matcher(password).matches()) {
            binding.txtErorPass.visibility = View.VISIBLE
            return false
        }
        binding.txtErorPass.visibility = View.GONE
        return true
    }

    private fun buttonEnabled() {
        var username = checkUsername()
        var email = checkEmail()
        var password = checkPasssword()


        binding.btnRegister.isEnabled = username && email && password

    }

    private fun register() {
        lifecycleScope.launch {
            val email = binding.edtEmail.text.toString().trim()
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            if (selectedFile.isNotEmpty()) {
                viewModel.startRegister(selectedFile[0], email, username, password)
            } else {
                viewModel.startRegister(null, email, username, password)
            }
        }
    }
}