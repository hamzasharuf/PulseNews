package com.hamzasharuf.pulse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamzasharuf.pulse.data.database.Dao.NewsDao
import com.hamzasharuf.pulse.data.database.Entities.ArticleDatabaseEntity
import com.hamzasharuf.pulse.data.database.AppDatabase.Companion.DATABASE_VERSION
import com.hamzasharuf.pulse.data.database.Dao.SourcesDao
import com.hamzasharuf.pulse.data.database.Entities.SourcesDatabaseEntity

@Database(
    entities = [ArticleDatabaseEntity::class, SourcesDatabaseEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false

)
abstract class AppDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao
    abstract val sourcesDao: SourcesDao

companion object{
    const val DATABASE_NAME = "news.db"
    const val DATABASE_VERSION = 2
}
}