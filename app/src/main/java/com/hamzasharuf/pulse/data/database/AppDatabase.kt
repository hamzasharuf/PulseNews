package com.hamzasharuf.pulse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamzasharuf.pulse.data.database.Dao.NewsDao
import com.hamzasharuf.pulse.data.database.Entities.ArticleDatabaseEntity
import com.hamzasharuf.pulse.data.database.AppDatabase.Companion.DATABASE_VERSION

@Database(
    entities = [ArticleDatabaseEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false

)
abstract class AppDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao

companion object{
    const val DATABASE_NAME = "news.db"
    const val DATABASE_VERSION = 1
}
}