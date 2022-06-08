package com.myappventure.app.ui.navigation.ui.profile.tentangkami

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
        R.drawable.devlin,
        "Devlin Aldyandi",
        "PM"
    ),
    TentangKamiData(
        R.drawable.cicil,
        "Cicilia Viyaya W",
        "UI/UX Design"
    ),
    TentangKamiData(
        R.drawable.tiara,
        "Tiara Azzelia P",
        "UI/UX Design"
    ),
    TentangKamiData(
        R.drawable.rahmah,
        "Rahmah Nur A",
        "Front End Developer"
    ),
    TentangKamiData(
        R.drawable.imelda,
        "Imelda Averina",
        "Front End Developer"
    ),
    TentangKamiData(
        R.drawable.aras,
        "M Arras Adazim",
        "Back End Developer"
    ),
    TentangKamiData(
        R.drawable.rizky,
        "M Rizky Wisesar",
        "Back End Developer"
    ),
    TentangKamiData(
        R.drawable.shinta,
        "Shinta Amyrul P",
        "Android Developer"
    ),
    TentangKamiData(
        R.drawable.surli,
        "Surli",
        "Android Developer"
    ),
    TentangKamiData(
        R.drawable.krisi,
        "Krissy Vieri",
        "QA Team"
    ),
    TentangKamiData(
        R.drawable.dei,
        "Daniella Deilova",
        "QA Team"
    ),

)