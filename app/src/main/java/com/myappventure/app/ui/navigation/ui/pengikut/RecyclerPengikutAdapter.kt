package com.myappventure.app.ui.navigation.ui.pengikut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.ItemMengikutiBinding
import com.myappventure.app.databinding.ItemPengikutBinding
import com.myappventure.app.ui.navigation.ui.mengikuti.RecyclerMengikutiAdapter

class RecyclerPengikutAdapter : RecyclerView.Adapter<RecyclerPengikutAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerPengikutAdapter.ViewHolder {
        val view = ItemPengikutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerPengikutAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 12

    inner class ViewHolder(var binding: ItemPengikutBinding) :
        RecyclerView.ViewHolder(binding.root)
}