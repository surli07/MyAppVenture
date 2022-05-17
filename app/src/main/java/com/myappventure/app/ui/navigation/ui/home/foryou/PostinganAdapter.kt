package com.myappventure.app.ui.navigation.ui.home.foryou

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.data.remote.getAllPostingan.Content
import com.myappventure.app.databinding.ItemPostinganBinding

class PostinganAdapter(
    var postingan: MutableList<Content>,
//    private val onClick: LoginActivity
) : RecyclerView.Adapter<PostinganAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPostinganBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPostinganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postingan = postingan[position]
        holder.binding.txtWaktuPost.text = postingan.createdDate
        holder.binding.txtDeskripsi.text = postingan.text
        holder.binding.txtJumlahLike.text = postingan.jumlahLike.toString()
        holder.binding.txtJumlahKomentar.text = postingan.jumlahKomentar.toString()
//        if (!MySharedPref.isLoggedIn){
//            holder.binding.btnIkuti.setOnClickListener {
//                onClick
//            }
//        }
    }

    override fun getItemCount() = postingan.size

}