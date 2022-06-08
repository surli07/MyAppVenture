package com.myappventure.app.ui.navigation.ui.profile.komunitas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.data.remote.komunitas.komunitas_saya.Content
import com.myappventure.app.databinding.ItemPageKomunitasBinding

class KomunitasSayaAdapter(
    var komunitasSaya: MutableList<Content>,
    val onDetail: (Content) -> Unit
) : RecyclerView.Adapter<KomunitasSayaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPageKomunitasBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemPageKomunitasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val komunitasSaya = komunitasSaya[position]
        holder.binding.txtNamaKomunitas.text = komunitasSaya.komunitas.namaKomunitas
        Glide.with(holder.itemView)
            .load(komunitasSaya.komunitas.urlFileName)
            .into(holder.binding.imgItemKomunitas)
        holder.binding.itemKomunitas.setOnClickListener {
            onDetail(komunitasSaya)
        }
    }

    override fun getItemCount() = komunitasSaya.size
}