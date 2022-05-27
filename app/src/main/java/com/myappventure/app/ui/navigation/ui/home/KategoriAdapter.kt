package com.myappventure.app.ui.navigation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.ItemKategoriBinding

class KategoriAdapter(
    private var kategori: List<KategoriData>
) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemKategoriBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = kategori[position]
        holder.binding.txtKategori.text = product.kategori
    }

    override fun getItemCount() = kategori.size
}