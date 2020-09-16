package com.hamzasharuf.pulse.di.modules

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.database.AppDatabase
import com.hamzasharuf.pulse.data.database.Dao.NewsDao
import com.hamzasharuf.pulse.data.mappers.ArticleDatabaseMapper
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.data.repositories.NewsRepositoryImpl
import com.hamzasharuf.pulse.data.mappers.ArticleNetworkMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceDatabaseMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceNetworkMapper
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
        newsSourceNetworkMapper: NewsSourceNetworkMapper,
        newsSourceDatabaseMapper: NewsSourceDatabaseMapper,
        articleNetworkMapper: ArticleNetworkMapper,
        articleDatabaseMapper: ArticleDatabaseMapper,
        appDatabase: AppDatabase
    ): NewsRepository{
        return NewsRepositoryImpl(
            newsApi,
            appDatabase.newsDao,
            appDatabase.sourcesDao,
            newsSourceNetworkMapper,
            newsSourceDatabaseMapper,
            articleNetworkMapper,
            articleDatabaseMapper
        )
    }
}