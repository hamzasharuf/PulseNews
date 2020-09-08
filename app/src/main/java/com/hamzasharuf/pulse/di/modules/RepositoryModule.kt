package com.hamzasharuf.pulse.di.modules

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.data.repositories.NewsRepositoryImpl
import com.hamzasharuf.pulse.data.mappers.ArticlesMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsSourceMapper: NewsSourceMapper,
        articlesMapper: ArticlesMapper
    ): NewsRepository{
        return NewsRepositoryImpl(newsApi, newsSourceMapper, articlesMapper)
    }
}