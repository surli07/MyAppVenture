package com.myappventure.app.ui.navigation.ui.komunitas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.data.remote.komunitas.list_komunitas.Content
import com.myappventure.app.data.remote.komunitas.list_komunitas.Data
import com.myappventure.app.databinding.ItemPageKomunitasBinding

class KomunitasAdapter(
    var komunitas: MutableList<Content>,
    val onClick: (data: Data) -> Unit
) : RecyclerView.Adapter<KomunitasAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPageKomunitasBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemPageKomunitasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val komunitas = komunitas[position]
        holder.binding.txtNamaKomunitas.text = komunitas.namaKomunitas
        Glide.with(holder.itemView)
            .load(komunitas.urlFileName)
            .into(holder.binding.imgItemKomunitas)
        holder.binding.itemKomunitas.setOnClickListener {
            onClick(komunitas.)
        }
    }

    override fun getItemCount() = komunitas.size
}