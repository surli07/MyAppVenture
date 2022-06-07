package com.myappventure.app.ui.navigation.ui.komunitas

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
import com.myappventure.app.data.remote.komunitas.detail_postingan_komunitas.Data
import com.myappventure.app.data.remote.komunitas.get_postingan_komunitas.KomentarBy
import com.myappventure.app.databinding.ActivityDetailPostinganBinding
import com.myappventure.app.ui.navigation.ui.komunitas.follow.KomentarPostinganKomunitasAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPostinganKomunitasActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailPostinganBinding
    private val detailPostinganKomunitasViewModel: DetailPostinganKomunitasViewModel by viewModels()
    private lateinit var detailPost: Data
    private var jumlahLike = 0
    private var jumlahKomentar = 0
    private var komentarAdapter = KomentarPostinganKomunitasAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPostinganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.edtKomentar.addTextChangedListener {
            validateText()
        }

        intent.getParcelableExtra<Data>("postinganKomunitas")?.let {
            detailPost = it
            jumlahLike = it.jumlahLike
            jumlahKomentar = it.jumlahKomentar
            if (MySharedPref.isLoggedIn) {
                binding.imgLike.setOnClickListener {
                    lifecycleScope.launch {
                        detailPostinganKomunitasViewModel.likePost(detailPost.id)
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
                        detailPostinganKomunitasViewModel.komentarPost(detailPost.id, text)
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
                adapter = KomentarPostinganKomunitasAdapter(it.komentarBy.toMutableList())
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
        detailPostinganKomunitasViewModel.likeResult.observe(this) {
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

        detailPostinganKomunitasViewModel.komentarResult.observe(this) {
            if (it.status == "200") {
                jumlahKomentar++
                binding.txtJumlahKomentar.text = jumlahKomentar.toString() + " Komentar"
                val komentar = KomentarBy(
                    it.data.createdDate,
                    null,
                    it.data.id,
                    it.data.jumlahBalasKomentar,
                    it.data.textKomentar,
                    it.data.updatedDate,
                    it.data.user
                )
                komentarAdapter.komentar.add(komentar)
                komentarAdapter.notifyItemInserted(komentarAdapter.komentar.size - 1)
                binding.edtKomentar.setText("")
            }
        }
    }
}