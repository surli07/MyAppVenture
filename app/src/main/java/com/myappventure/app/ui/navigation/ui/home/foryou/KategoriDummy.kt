package com.myappventure.app.ui.navigation.ui.home.foryou

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KategoriDummy(
    val name: String
) : Parcelable

fun kategoriDataDummy() = listOf(
    KategoriDummy("Pantai")
)