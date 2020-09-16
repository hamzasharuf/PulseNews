package com.hamzasharuf.pulse.data.api.interceptors

import com.hamzasharuf.networkmonitor.ConnectivityStateHolder
import com.hamzasharuf.pulse.utils.exceptions.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectivityInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return when (ConnectivityStateHolder.isConnected) {
            true -> chain.proceed(chain.request())
            false -> throw NoInternetException("No internet connection")
        }
    }
}