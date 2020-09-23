package com.hamzasharuf.pulse.di.modules

import com.hamzasharuf.pulse.data.api.BitmapLoaderApi
import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.clients.BitmapLoaderApiClient
import com.hamzasharuf.pulse.data.api.clients.NewsApiClient
import com.hamzasharuf.pulse.data.api.clients.NewsOkHttpClient
import com.hamzasharuf.pulse.data.api.interceptors.AppendApiKeyInterceptor
import com.hamzasharuf.pulse.data.api.interceptors.NetworkConnectivityInterceptor
import com.hamzasharuf.pulse.di.annotations.BitmapClient
import com.hamzasharuf.pulse.di.annotations.NewsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object BitmapLoaderApiModule {

    @Singleton
    @Provides
    fun provideBitmapApi(): BitmapLoaderApi {
        return BitmapLoaderApiClient()
    }

}