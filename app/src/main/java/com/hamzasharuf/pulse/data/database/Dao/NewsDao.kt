package com.hamzasharuf.pulse.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hamzasharuf.pulse.data.database.Entities.ArticleDatabaseEntity

@Dao
abstract class NewsDao {

    @Transaction
    open suspend fun update(newsList: List<ArticleDatabaseEntity>){
        delete()
        insert(newsList)
    }

    @Insert
    abstract suspend fun insert(newsList: List<ArticleDatabaseEntity>)

    @Query("SELECT * FROM articles")
    abstract suspend fun getNews(): List<ArticleDatabaseEntity>

    @Query("DELETE FROM articles")
    abstract suspend fun delete()
}