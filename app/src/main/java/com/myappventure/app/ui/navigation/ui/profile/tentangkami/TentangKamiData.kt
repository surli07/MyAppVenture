package com.myappventure.app.ui.navigation.ui.profile.tentangkami

import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.myappventure.app.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class TentangKamiData (
    val image: Int,
    val nama: String,
    val jabatan: String
) : Parcelable

fun dataTentangDummy() = listOf(
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),
    TentangKamiData(
        R.drawable.ic_launcher_background,
        "nama",
        "jabatan"
    ),

)