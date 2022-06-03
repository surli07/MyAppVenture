package com.myappventure.app.ui.navigation.ui.home.detail_postingan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.TimeAgo.toTimeAgo
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.data.remote.getAllPostingan.KomentarBy
import com.myappventure.app.databinding.ItemKomentarBinding
import com.myappventure.app.ui.navigation.ui.home.foryou.PostinganAdapter

class KomentarAdapter(
    var komentar: MutableList<KomentarBy>
) : RecyclerView.Adapter<KomentarAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemKomentarBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemKomentarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = komentar.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val komentar = komentar[position]
        holder.binding.txtNama.text = komentar.user.nama
        holder.binding.txtKomentar.text = komentar.textKomentar
        holder.binding.txtWaktuPost.text = komentar.createdDate.toTimeAgo()
        if (komentar.user.urlFileName.isNotEmpty()){
            holder.binding.imgFoto.visibility = View.GONE
            Glide.with(holder.itemView)
                .load(komentar.user.urlFileName)
                .placeholder(R.drawable.icon_aplikasi)
                .into(holder.binding.imgFoto1)
        }
    }

}