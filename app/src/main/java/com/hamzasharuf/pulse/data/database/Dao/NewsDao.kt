package com.hamzasharuf.pulse.data.database.Dao

import androidx.room.*
import com.hamzasharuf.pulse.data.database.Entities.NewsDatabaseEntity

@Dao
abstract class NewsDao {

    // Note : This is an expensive operation that has to be avoided as stated in the official documentation
    // https://developer.android.com/training/data-storage/room/accessing-data#kotlin-coroutines
    @Transaction
    open suspend fun update(newsList: List<NewsDatabaseEntity>, section: String){
        delete(section)
        insert(newsList)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(newsList: List<NewsDatabaseEntity>)

    @Query("SELECT * FROM ${NewsDatabaseEntity.TABLE_NAME} WHERE sectionName LIKE :section")
    abstract suspend fun getNews(section: String): List<NewsDatabaseEntity>

    @Query("DELETE FROM ${NewsDatabaseEntity.TABLE_NAME} WHERE sectionName LIKE :section")
    abstract suspend fun delete(section: String)

    @Query("SELECT title FROM ${NewsDatabaseEntity.TABLE_NAME} WHERE sectionName LIKE :section LIMIT 1")
    abstract suspend fun getSingleItem(section: String): String?
}