package com.myappventure.app.ui.navigation.ui.home.detail_postingan

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.myappventure.app.R
import com.myappventure.app.TimeAgo.toTimeAgo
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.data.remote.getAllPostingan.KomentarBy
import com.myappventure.app.databinding.ActivityDetailPostinganBinding
import com.myappventure.app.ui.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPostinganActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailPostinganBinding
    private val detailPostinganViewModel: DetailPostinganViewModel by viewModels()
    private lateinit var detailPost: Content
    private var jumlahLike = 0
    private var jumlahKomentar = 0
    private var komentarAdapter = KomentarAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPostinganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.edtKomentar.addTextChangedListener {
            validateText()
        }

        intent.getParcelableExtra<Content>("postingan")?.let {
            detailPost = it
            jumlahLike = it.jumlahLike
            jumlahKomentar = it.jumlahKomentar
            if (MySharedPref.isLoggedIn) {
                binding.imgLike.setOnClickListener {
                    lifecycleScope.launch {
                        detailPostinganViewModel.likePost(detailPost.id)
                    }
                }
                val iduser = MySharedPref.idUser!!
                it.likedBy.find { like ->
                    like.user.id == iduser
                }?.let {
                    binding.imgLike.setImageResource(R.drawable.ic_love_full)
                }
                binding.imgSend.setOnClickListener {
                    lifecycleScope.launch {
                        val text = binding.edtKomentar.text.toString().trim()
                        detailPostinganViewModel.komentarPost(detailPost.id, text)
                    }
                }
            }
            val imageList = ArrayList<SlideModel>()
            if (it.filePosts.isNotEmpty()) {
                binding.imgSlider.visibility = View.VISIBLE
                var file = it.filePosts
                for (f in file) {
                    imageList.add(SlideModel(f.url, ScaleTypes.CENTER_CROP))
                }
                binding.imgSlider.setImageList(imageList)
            } else {
                binding.imgSlider.visibility = View.GONE
            }
            if (it.user.urlFileName.isNotEmpty()) {
                binding.imgFoto.visibility = View.GONE
                binding.imgPhotoUser.visibility = View.VISIBLE
                Glide.with(binding.imgPhotoUser.context)
                    .load(it.user.urlFileName)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imgPhotoUser)
            } else {
                binding.imgFoto.visibility = View.VISIBLE
                binding.imgPhotoUser.visibility = View.GONE
            }
            binding.txtNamaUser.text = it.user.nama
            binding.txtWaktuPost.text = it.createdDate.toTimeAgo()
            binding.txtDeskripsi.text = it.text
            binding.txtDisukai.text = jumlahLike.toString() + " Disukai"
            binding.txtJumlahKomentar.text = jumlahKomentar.toString() + " Komentar"
            binding.recyclerKomentar.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
                adapter = KomentarAdapter(it.komentarBy.toMutableList())
            }

        }

        setupObserver()

    }

    private fun validateText() {
        val email = binding.edtKomentar.text.toString().trim()
        when {
            email.isEmpty() -> {
                binding.imgSend.isEnabled = false
            }
            else -> {
                binding.imgSend.isEnabled = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.edit_delete_menu, menu)
        return true
    }

    override fun setupObserver() {
        detailPostinganViewModel.likeResult.observe(this) {
            if (it.data.reaction == true) {
                jumlahLike++
                binding.imgLike.setImageResource(
                    R.drawable.ic_love_full
                )
                binding.txtDisukai.text = jumlahLike.toString() + " Disukai"
            } else {
                jumlahLike--
                binding.imgLike.setImageResource(
                    R.drawable.ic_like
                )
                binding.txtDisukai.text = jumlahLike.toString() + " Disukai"
            }
        }

        detailPostinganViewModel.komentarResult.observe(this){
            if(it.status == "200"){
                jumlahKomentar++
                binding.txtJumlahKomentar.text = jumlahKomentar.toString() + " Komentar"
                val komentar = KomentarBy(
                    it.data.createdDate, null,
                    it.data.id, it.data.jumlahBalasKomentar, it.data.textKomentar, it.data.updatedDate, it.data.user
                )
                komentarAdapter.komentar.add(komentar)
                komentarAdapter.notifyItemInserted(komentarAdapter.komentar.size -1)
                binding.edtKomentar.setText("")
            }
        }
    }
}