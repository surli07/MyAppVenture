package com.myappventure.app.data.remote.komentar

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class KomentarBody (
    @SerializedName("textKomentar")
    val textKomentar: String
) : Parcelable