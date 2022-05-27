package com.myappventure.app.ui.navigation.ui.komunitas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.data.remote.komunitas.detail_komunitas.Data
import com.myappventure.app.databinding.ActivityDetailKomunitasBinding
import com.myappventure.app.ui.navigation.ui.destinasi.DestinasiAdapter

class DetailKomunitasAdapter(
    var komunitas: MutableList<Data>
) : RecyclerView.Adapter<DetailKomunitasAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ActivityDetailKomunitasBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ActivityDetailKomunitasBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val komunitas = komunitas[position]
        holder.binding.txtNamaKomunitas.text = komunitas.namaKomunitas
        holder.binding.txtDeskripsiKomunitas.text = komunitas.deskripsi
        holder.binding.txtLinkGrup.text = komunitas.linkKomunitas
        Glide.with(holder.itemView)
            .load(komunitas.urlFileName)
            .into(holder.binding.imgFoto)
    }

    override fun getItemCount() = komunitas.size
}