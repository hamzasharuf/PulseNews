package com.hamzasharuf.pulse.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface BitmapLoaderApi {
    @GET
    suspend fun getImageData(@Url url: String): ResponseBody
}