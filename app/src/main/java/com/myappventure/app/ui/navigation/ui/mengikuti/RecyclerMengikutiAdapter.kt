package com.myappventure.app.ui.navigation.ui.mengikuti

import android.view.LayoutInflater
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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 12

    inner class ViewHolder(var binding: ItemMengikutiBinding) :
        RecyclerView.ViewHolder(binding.root)
}