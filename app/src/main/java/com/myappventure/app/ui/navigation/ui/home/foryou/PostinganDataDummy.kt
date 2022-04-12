package com.myappventure.app.ui.navigation.ui.home.foryou

import android.os.Parcelable
import com.myappventure.app.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostinganDataDummy(
    val photo: Int,
    val name: String,
    val time: String,
    val deskripsi: String,
    val imgLike: Int,
    val jumlahLike: Int,
    val imgKomentar: Int,
    val jumlahKomentar: Int,
    val imgShare: Int
) : Parcelable

fun postinganDataDummy() = listOf(
    PostinganDataDummy(
        R.mipmap.ic_launcher,
        "Kiki",
        "10 menit lalu",
        "Saya sering kali saya melihat orang - orang dengan tidak sengaja membuang sampah sembarangan yang membuat lingkungan disekitar kita kotor. Pernah sekali saya menegur orang - orang yang membuang sampah sembarangan namun mereka malah tidak terima. teman - teman jangan seperti itu yaa! Tetap buanglah sampah pada tempatnya! Jaga Alam kita!",
        R.drawable.ic_like,
        60,
        R.drawable.ic_komentar,
        10,
        R.drawable.ic_link
    ),
    PostinganDataDummy(
        R.mipmap.ic_launcher,
        "Satria",
        "10 menit lalu",
        "Saya sering kali saya melihat orang - orang dengan tidak sengaja membuang sampah sembarangan yang membuat lingkungan disekitar kita kotor. Pernah sekali saya menegur orang - orang yang membuang sampah sembarangan namun mereka malah tidak terima. teman - teman jangan seperti itu yaa! Tetap buanglah sampah pada tempatnya! Jaga Alam kita!",
        R.drawable.ic_like,
        60,
        R.drawable.ic_komentar,
        10,
        R.drawable.ic_link
    )
)
