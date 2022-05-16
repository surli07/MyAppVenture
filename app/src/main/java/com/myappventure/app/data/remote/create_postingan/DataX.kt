package com.myappventure.app.data.remote.create_postingan


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("deleted_date")
    val deletedDate: Any,
    @SerializedName("fileName1")
    val fileName1: String,
    @SerializedName("fileName10")
    val fileName10: String,
    @SerializedName("fileName2")
    val fileName2: String,
    @SerializedName("fileName3")
    val fileName3: String,
    @SerializedName("fileName4")
    val fileName4: String,
    @SerializedName("fileName5")
    val fileName5: String,
    @SerializedName("fileName6")
    val fileName6: String,
    @SerializedName("fileName7")
    val fileName7: String,
    @SerializedName("fileName8")
    val fileName8: String,
    @SerializedName("fileName9")
    val fileName9: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumlahKomentar")
    val jumlahKomentar: Int,
    @SerializedName("jumlahLike")
    val jumlahLike: Int,
    @SerializedName("postIn")
    val postIn: Any,
    @SerializedName("text")
    val text: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("urlFileName1")
    val urlFileName1: String,
    @SerializedName("urlFileName10")
    val urlFileName10: String,
    @SerializedName("urlFileName2")
    val urlFileName2: String,
    @SerializedName("urlFileName3")
    val urlFileName3: String,
    @SerializedName("urlFileName4")
    val urlFileName4: String,
    @SerializedName("urlFileName5")
    val urlFileName5: String,
    @SerializedName("urlFileName6")
    val urlFileName6: String,
    @SerializedName("urlFileName7")
    val urlFileName7: String,
    @SerializedName("urlFileName8")
    val urlFileName8: String,
    @SerializedName("urlFileName9")
    val urlFileName9: String,
    @SerializedName("user")
    val user: User
)