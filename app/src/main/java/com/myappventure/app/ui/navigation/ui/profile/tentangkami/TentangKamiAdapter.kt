package com.myappventure.app.ui.navigation.ui.profile.tentangkami

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.ItemAbouUsBinding

class TentangKamiAdapter (
    private var image: List<TentangKamiData>
) : RecyclerView.Adapter<TentangKamiAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemAbouUsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemAbouUsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tim = image[position]
        holder.binding.imgTim.setImageResource(tim.image)
        holder.binding.txtNama.text = tim.nama
        holder.binding.txtJabatan.text = tim.jabatan
    }

    override fun getItemCount() = image.size
}