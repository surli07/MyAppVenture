package com.myappventure.app.ui.navigation.ui.home.foryou

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.ItemPostinganBinding

sealed class PostinganAdapter(
    private val postingan: List<PostinganDataDummy>
) : RecyclerView.Adapter<PostinganAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPostinganBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPostinganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postingan = postingan[position]
        holder.binding.cardPhoto.setBackgroundResource(postingan.photo)
        holder.binding.txtNamaUser.text = postingan.name
        holder.binding.txtWaktuPost.text = postingan.time
        holder.binding.txtDeskripsi.text = postingan.deskripsi
        holder.binding.imgLike.setImageResource(postingan.imgLike)
        holder.binding.txtJumlahLike.text = postingan.jumlahLike.toString()
        holder.binding.imgKomentar.setImageResource(postingan.imgKomentar)
        holder.binding.txtJumlahKomentar.text= postingan.jumlahKomentar.toString()
        holder.binding.imgShare.setImageResource(postingan.imgShare)
    }

    override fun getItemCount() = postingan.size


}