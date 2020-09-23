package com.hamzasharuf.pulse.data.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL

@Parcelize
data class News(
    val title: String,
    val section: String,
    val authors: String?,
    val date: String,
    val url: String,
    val thumbnail: String,
    val trailTextHtml: String,
    val articleBody: String,
    var thumbnailBitmap: Bitmap? = null
) : Parcelable{

}