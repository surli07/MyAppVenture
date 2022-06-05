package com.myappventure.app.ui.navigation.ui.pengikut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.data.remote.follow.listFollower.Content
import com.myappventure.app.databinding.ItemPengikutBinding

class RecyclerPengikutAdapter : RecyclerView.Adapter<RecyclerPengikutAdapter.ViewHolder>() {
    var pengikut = mutableListOf<Content>()

    inner class ViewHolder(val binding: ItemPengikutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerPengikutAdapter.ViewHolder {
        val view = ItemPengikutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pengikut = pengikut[position]
        if (pengikut.userFollower.fileName != null) {
            holder.binding.imgPengikut.visibility = View.GONE
            holder.binding.imgPengikut.visibility = View.VISIBLE
            Glide.with(holder.itemView)
                .load(pengikut.userFollower.urlFileName)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.binding.imgPengikut)
        } else {
            holder.binding.imgPengikut.visibility = View.VISIBLE
            holder.binding.imgPengikut.visibility = View.GONE
        }
        holder.binding.btnIkuti.setOnClickListener {
            holder.binding.btnMengikuti.visibility = View.VISIBLE
            holder.binding.btnIkuti.visibility = View.GONE
        }
        holder.binding.btnMengikuti.setOnClickListener {
            holder.binding.btnIkuti.visibility = View.VISIBLE
            holder.binding.btnMengikuti.visibility = View.GONE
        }
        holder.binding.txtNamaPengikut.text = pengikut.userFollower.nama
    }

    override fun getItemCount() = pengikut.size

}