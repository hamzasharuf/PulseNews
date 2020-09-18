package com.hamzasharuf.pulse.data.api.interceptors

import android.content.Context
import com.hamzasharuf.pulse.utils.NetworkUtils
import com.hamzasharuf.pulse.utils.exceptions.NoInternetException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectivityInterceptor @Inject constructor(
    @ApplicationContext val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return when (NetworkUtils.checkInternetAvailability(context)) {
            true -> chain.proceed(chain.request())
            false -> throw NoInternetException("No internet connection")
        }
    }
}