package com.myappventure.app.data.remote.destinasi.createDestinasi


import com.google.gson.annotations.SerializedName

data class StackTrace(
    @SerializedName("classLoaderName")
    val classLoaderName: Any,
    @SerializedName("className")
    val className: String,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("lineNumber")
    val lineNumber: Int,
    @SerializedName("methodName")
    val methodName: String,
    @SerializedName("moduleName")
    val moduleName: Any,
    @SerializedName("moduleVersion")
    val moduleVersion: Any,
    @SerializedName("nativeMethod")
    val nativeMethod: Boolean
)