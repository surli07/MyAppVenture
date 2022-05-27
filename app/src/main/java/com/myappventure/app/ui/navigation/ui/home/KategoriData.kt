package com.myappventure.app.ui.navigation.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KategoriData(
    val kategori: String
) : Parcelable

fun kategoriDataDummy() = listOf(
    KategoriData(
        "Pendaki"
    ),
    KategoriData(
        "Pantai"
    ),
    KategoriData(
        "Hutan"
    ),
    KategoriData(
        "Tanaman"
    ),
    KategoriData(
        "Air Terjun"
    )

)
