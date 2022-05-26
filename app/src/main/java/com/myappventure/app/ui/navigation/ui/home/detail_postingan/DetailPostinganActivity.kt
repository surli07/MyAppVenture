package com.myappventure.app.ui.navigation.ui.home.detail_postingan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.myappventure.app.TimeAgo.toTimeAgo
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.databinding.ActivityDetailPostinganBinding
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostinganActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailPostinganBinding
    private val detailPostinganViewModel: DetailPostinganViewModel by viewModels()
    private lateinit var detailPost: Content


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPostinganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }

        intent.extras?.getParcelable<Content>("postingan")?.let {
            detailPost = it
            if (it.filePosts?.isEmpty()) {
                binding.imgSlider.visibility = View.GONE
            } else {
                binding.imgSlider.visibility = View.VISIBLE
            }
            if (it.user.urlFileName.isNotEmpty()) {
                binding.imgFoto.visibility = View.GONE
                binding.imgPhotoUser.visibility = View.VISIBLE
                Glide.with(binding.imgPhotoUser.context)
                    .load(it.user.urlFileName)
                    .into(binding.imgPhotoUser)
            } else {
                binding.imgFoto.visibility = View.VISIBLE
                binding.imgPhotoUser.visibility = View.GONE
            }
            binding.txtNamaUser.text = it.user.nama
            binding.txtWaktuPost.text = it.createdDate.toTimeAgo()
            binding.txtDeskripsi.text = it.text
        }

        setupObserver()

    }

    override fun setupObserver() {
        //TODO("Not yet implemented")
    }
}