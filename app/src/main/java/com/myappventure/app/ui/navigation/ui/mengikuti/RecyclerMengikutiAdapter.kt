package com.myappventure.app.ui.navigation.ui.mengikuti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.ItemMengikutiBinding

class RecyclerMengikutiAdapter : RecyclerView.Adapter<RecyclerMengikutiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerMengikutiAdapter.ViewHolder {
        val view = ItemMengikutiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerMengikutiAdapter.ViewHolder, position: Int) {
        holder.binding.btnIkuti.setOnClickListener {
            holder.binding.btnMengikuti.visibility = View.VISIBLE
            holder.binding.btnIkuti.visibility = View.GONE
        }
        holder.binding.btnMengikuti.setOnClickListener {
            holder.binding.btnIkuti.visibility = View.VISIBLE
            holder.binding.btnMengikuti.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = 12

    inner class ViewHolder(var binding: ItemMengikutiBinding) :
        RecyclerView.ViewHolder(binding.root)
}