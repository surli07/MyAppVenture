package com.myappventure.app.ui.register

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
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityRegisterBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.regex.Pattern
import kotlin.math.roundToInt
import kotlin.math.sqrt

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private val requestGetPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedUri: Uri? = result.data?.data
            selectedUri?.let { uri ->
                binding.imgPhotoUser.setImageURI(uri)
                binding.imgPhotoUser.visibility = View.VISIBLE
                binding.imgPhoto.visibility = View.GONE
                createFileBeforeUpload(uri)
            }
        } else {
            binding.imgPhotoUser.visibility = View.GONE
            Toast.makeText(
                this,
                "Pemilihan gambar dibatalkan",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    private val usernameRegex = Pattern.compile("[a-z]{3,15}")
    private val passwordREGEX = Pattern.compile("(?=\\S+$).{6,}$")
    private var file = File("")

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
            binding.edtPassword.inputType = View.AUTOFILL_TYPE_TEXT
            binding.imgLockPass.visibility = View.GONE
            binding.imgOpenPass.visibility = View.VISIBLE
        }
        binding.imgOpenPass.setOnClickListener {
            binding.edtPassword.inputType = View.AUTOFILL_HINT_PASSWORD.toInt()
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
                            this@RegisterActivity.contentResolver.openInputStream(
                                uri
                            )
                        )
                    val targetCompressed = reduceBitmapSize(targetOriginal)
                    val bos = ByteArrayOutputStream()
                    targetCompressed.compress(Bitmap.CompressFormat.JPEG, 0, bos)
                    val bitmapData = bos.toByteArray()

                    file = File(
                        this@RegisterActivity.filesDir.path,
                        "${System.currentTimeMillis()}_compressed.jpeg"
                    )
                    file.createNewFile()
                    val fos = FileOutputStream(file)
                    fos.write(bitmapData)
                    fos.flush()
                    fos.close()

                    // lempar file ke view model untuk diupload retrofit

                } catch (e: Exception) {
                    this@RegisterActivity.runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Terjadi kesalahan saat menulis file",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun getFile(file: File) : File{
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
        viewModel.registerResponse.observe(this){
            val i = Intent(this@RegisterActivity, SuksesRegisterActivity::class.java)
            startActivity(i)
            finish()
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
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString()
            if(file.isFile){
                viewModel.startRegister(file, email, username, password)
            }else{
                viewModel.startRegister(null, email, username, password)
            }
            setupObserver()
        }
    }
}