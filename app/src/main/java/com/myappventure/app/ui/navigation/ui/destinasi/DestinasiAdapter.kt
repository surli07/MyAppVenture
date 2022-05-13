package com.myappventure.app.ui.navigation.ui.destinasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.data.remote.destinasi.AllListDestinasi.Content
import com.myappventure.app.databinding.ItemDestinasiBinding

class DestinasiAdapter(
    var destinasi: MutableList<Content>
) : RecyclerView.Adapter<DestinasiAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemDestinasiBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDestinasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destinasi = destinasi[position]
        holder.binding.txtLokasi.text = destinasi.lokasi.toString()
    }

    override fun getItemCount() = destinasi.size
}