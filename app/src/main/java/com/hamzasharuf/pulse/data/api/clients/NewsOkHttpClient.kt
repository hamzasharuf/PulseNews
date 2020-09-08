package com.hamzasharuf.pulse.data.api.clients

import com.hamzasharuf.pulse.data.api.interceptors.AppendApiKeyInterceptor
import com.hamzasharuf.pulse.data.api.interceptors.LoggingInterceptor
import com.hamzasharuf.pulse.data.api.interceptors.NetworkConnectivityInterceptor
import okhttp3.OkHttpClient

class NewsOkHttpClient {

    companion object {

        operator fun invoke(
            appendApiKeyInterceptor: AppendApiKeyInterceptor,
            networkConnectivityInterceptor: NetworkConnectivityInterceptor
        ): OkHttpClient {
            return UnsafeOkHttpClient.getUnsafeClient()
                .addInterceptor(appendApiKeyInterceptor)
                .addInterceptor(LoggingInterceptor.getHeadersLevelInterceptor())
                .addInterceptor(LoggingInterceptor.getBodyLevelInterceptor())
                .addNetworkInterceptor(networkConnectivityInterceptor)
                .build()
        }
    }
}