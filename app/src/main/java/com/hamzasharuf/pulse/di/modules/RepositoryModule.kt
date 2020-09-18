package com.hamzasharuf.pulse.di.modules

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

//    @Provides
//    @Singleton
//    fun provideNewsRepository(
//        newsApi: NewsApi,
//        appDatabase: AppDatabase
//    ): NewsRepository{
//        return NewsRepositoryImpl(
//            newsApi,
//            appDatabase.newsDao,
//            appDatabase.sourcesDao,
//        )
//    }
}