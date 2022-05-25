package com.myappventure.app.ui.navigation.ui.home.follow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.TimeAgo.toTimeAgo
import com.myappventure.app.data.remote.getPostByFollowing.Content
import com.myappventure.app.databinding.ItemPostinganBinding

class PostinganDiikutiAdapter (
    var postingan: MutableList<Content>
) : RecyclerView.Adapter<PostinganDiikutiAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPostinganBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostinganDiikutiAdapter.ViewHolder {
        val view = ItemPostinganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postingan = postingan[position]
        Glide.with(holder.itemView)
            .load(postingan.user.urlFileName)
            .into(holder.binding.imgProfile)
        holder.binding.txtNamaUser.text = postingan.user.nama
        holder.binding.txtWaktuPost.text = postingan.createdDate.toTimeAgo()
        holder.binding.txtDeskripsi.text = postingan.text
        holder.binding.txtJumlahLike.text = postingan.jumlahLike.toString()
        holder.binding.txtJumlahKomentar.text = postingan.jumlahKomentar.toString()
        holder.binding.btnIkuti.visibility = View.GONE
    }

    override fun getItemCount() = postingan.size
}