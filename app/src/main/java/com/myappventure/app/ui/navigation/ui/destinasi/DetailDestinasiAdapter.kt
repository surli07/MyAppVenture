package com.myappventure.app.ui.navigation.ui.destinasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.data.remote.destinasi.detailDestinasi.Data
import com.myappventure.app.databinding.ActivityDetailDestinasiBinding

class DetailDestinasiAdapter(
    var destinasi: MutableList<Data>
) : RecyclerView.Adapter<DetailDestinasiAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ActivityDetailDestinasiBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ActivityDetailDestinasiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destinasi = destinasi[position]
        holder.binding.txtJudulDestinasi.text = destinasi.nama
        holder.binding.txtDestinasi.text = destinasi.nama
        holder.binding.txtDetailDestinasi.text = destinasi.tentang
        holder.binding.txtHarga.text = destinasi.harga
        holder.binding.txtJam.text = destinasi.waktu
        Glide.with(holder.itemView)
            .load(destinasi.urlFileName)
            .into(holder.binding.imgFoto)
    }

    override fun getItemCount() = destinasi.size
}