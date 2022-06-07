package com.myappventure.app.ui.navigation.ui.komunitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.myappventure.app.R
import com.myappventure.app.TimeAgo.toTimeAgo
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.komunitas.get_postingan_komunitas.Content
import com.myappventure.app.databinding.ItemPostinganBinding

class PostinganKomunitasAdapter(
    var postinganKomunitas: MutableList<Content>,
    val onClick: () -> Unit,
    val onDetail: (Content) -> Unit,
    val onLike: (Int) -> Unit
) : RecyclerView.Adapter<PostinganKomunitasAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPostinganBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var idUser = MySharedPref.idUser

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPostinganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postinganKomunitas = postinganKomunitas[position]
        val imageList = ArrayList<SlideModel>()
        if (postinganKomunitas.user.fileName != null) {
            holder.binding.imgProfile.visibility = View.GONE
            holder.binding.imgPhotoUser.visibility = View.VISIBLE
            Glide.with(holder.itemView)
                .load(postinganKomunitas.user.urlFileName)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.binding.imgPhotoUser)
        } else {
            holder.binding.imgProfile.visibility = View.VISIBLE
            holder.binding.imgPhotoUser.visibility = View.GONE
        }

        if (postinganKomunitas.filePosts.isNotEmpty()) {
            holder.binding.imgSlider.visibility = View.VISIBLE
            var file = postinganKomunitas.filePosts
            for (f in file) {
                imageList.add(SlideModel(f.url, ScaleTypes.CENTER_CROP))
            }
            holder.binding.imgSlider.setImageList(imageList)
        } else {
            holder.binding.imgSlider.visibility = View.GONE
        }

        holder.binding.txtNamaUser.text = postinganKomunitas.user.nama
        holder.binding.txtWaktuPost.text = postinganKomunitas.createdDate.toTimeAgo()
        holder.binding.txtDeskripsi.text = postinganKomunitas.text
        holder.binding.txtJumlahLike.text = postinganKomunitas.jumlahLike.toString()
        holder.binding.txtJumlahKomentar.text = postinganKomunitas.jumlahKomentar.toString()
        if (postinganKomunitas.user.id == idUser) {
            holder.binding.btnIkuti.visibility = View.GONE
        }
        holder.binding.btnIkuti.setOnClickListener {
            onClick()
        }
        holder.binding.cardPostingan.setOnClickListener {
            onDetail(postinganKomunitas)
        }

        if (idUser != null) {
            holder.binding.imgLike.setOnClickListener {
                onLike(postinganKomunitas.id)
            }
            val find = postinganKomunitas.likedBy.find { like ->
                like.user.id == idUser
            }
            holder.binding.imgLike.setImageResource(
                if (find != null) {
                    R.drawable.ic_love_full_small
                } else {
                    R.drawable.ic_like
                }
            )
        }
    }

    override fun getItemCount() = postinganKomunitas.size

}