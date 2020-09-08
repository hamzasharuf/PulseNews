package com.hamzasharuf.pulse.data.api.clients

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.NewsApiConfiguration.BASE_URL
import com.hamzasharuf.pulse.di.annotations.NewsClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiClient {

    operator fun invoke(
        @NewsClient newsClient: OkHttpClient,
    ): NewsApi {
        return getRetrofit(newsClient).create(NewsApi::class.java)
    }

    private fun getRetrofit(newsClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(newsClient)
            .build()
    }
}