package com.hamzasharuf.pulse.di.modules

import android.content.Context
import androidx.room.Room
import com.hamzasharuf.pulse.data.database.AppDatabase
import com.hamzasharuf.pulse.data.database.AppDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NewsRoomModule {

    @Provides
    @Singleton
    fun provideNewsRoom(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
}