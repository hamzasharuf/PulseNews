package com.hamzasharuf.pulse.data.api.interceptors

import com.hamzasharuf.pulse.data.api.NewsApiConfiguration.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppendApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter("api-key", API_KEY).build()
        val newRequest = chain.request().newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

}