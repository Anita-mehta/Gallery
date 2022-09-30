package com.bhanguz.mvvmhilt.data.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageEntity (
    val id: String,
    val author: String? = null,
    val width:Int ?=null,
    val height:Int ? = null,
    val url:String ? = null,
  @SerializedName("download_url") val downloader_url :String ? = null
):Parcelable