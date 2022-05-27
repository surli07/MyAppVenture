package com.myappventure.app.ui.navigation.ui.home.foryou

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.TimeAgo.toTimeAgo
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.databinding.ItemPostinganBinding

class PostinganAdapter(
    var postingan: MutableList<Content>,
    val onClick: () -> Unit,
    val onDetail: (Content) -> Unit
) : RecyclerView.Adapter<PostinganAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPostinganBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPostinganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postingan = postingan[position]
        if (postingan.user.fileName != null) {
            holder.binding.imgProfile.visibility = View.GONE
            holder.binding.imgPhotoUser.visibility = View.VISIBLE
            Glide.with(holder.itemView)
                .load(postingan.user.urlFileName)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.binding.imgPhotoUser)
        } else {
            holder.binding.imgProfile.visibility = View.VISIBLE
            holder.binding.imgPhotoUser.visibility = View.GONE
        }

        if (postingan.filePosts.isNotEmpty()){
            holder.binding.imgSlider.visibility = View.VISIBLE
//            Glide.with(holder.itemView)
//                .load(postingan.urlFileName1)
//                .into(holder.binding.imgSlider)
        } else {
            holder.binding.imgSlider.visibility = View.GONE
        }

        holder.binding.txtNamaUser.text = postingan.user.nama
        holder.binding.txtWaktuPost.text = postingan.createdDate.toTimeAgo()
        holder.binding.txtDeskripsi.text = postingan.text
        holder.binding.txtJumlahLike.text = postingan.jumlahLike.toString()
        holder.binding.txtJumlahKomentar.text = postingan.jumlahKomentar.toString()
        holder.binding.btnIkuti.setOnClickListener {
            onClick()
        }
        holder.binding.cardPostingan.setOnClickListener {
            onDetail(postingan)
        }
    }

    override fun getItemCount() = postingan.size

}