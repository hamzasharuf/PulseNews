package com.hamzasharuf.pulse.data.api.clients

import com.hamzasharuf.pulse.data.api.BitmapLoaderApi
import com.hamzasharuf.pulse.data.api.NewsApiConfiguration.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BitmapLoaderApiClient {

    operator fun invoke(): BitmapLoaderApi {
        return getRetrofit().create(BitmapLoaderApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}