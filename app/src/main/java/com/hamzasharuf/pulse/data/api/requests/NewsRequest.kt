package com.hamzasharuf.pulse.data.api.requests

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import timber.log.Timber

data class NewsRequest(
    val section: String = "world",
    val page: Int = 1,
    @SerializedName("page-size")
    val pageSize: Int = 10,
    ){
}