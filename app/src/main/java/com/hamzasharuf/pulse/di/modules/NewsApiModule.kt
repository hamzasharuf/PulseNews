package com.hamzasharuf.pulse.di.modules

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.clients.NewsApiClient
import com.hamzasharuf.pulse.data.api.clients.NewsOkHttpClient
import com.hamzasharuf.pulse.data.api.interceptors.AppendApiKeyInterceptor
import com.hamzasharuf.pulse.data.api.interceptors.NetworkConnectivityInterceptor
import com.hamzasharuf.pulse.di.annotations.NewsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NewsApiModule {

    @NewsClient
    @Singleton
    @Provides
    fun provideNewsOkHttpClient(
        appendApiKeyInterceptor: AppendApiKeyInterceptor,
        networkConnectivityInterceptor: NetworkConnectivityInterceptor
    ): OkHttpClient {
        return NewsOkHttpClient(appendApiKeyInterceptor, networkConnectivityInterceptor)
    }

    @Singleton
    @Provides
    fun provideNewsApi(
        @NewsClient okHttpClient: OkHttpClient
    ): NewsApi {
        return NewsApiClient(okHttpClient)
    }

}