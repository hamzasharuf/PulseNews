package com.hamzasharuf.pulse.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hamzasharuf.pulse.data.database.Entities.SourcesDatabaseEntity

@Dao
abstract class SourcesDao {

    @Transaction
    open suspend fun update(sourcesList: List<SourcesDatabaseEntity>){
        delete()
        insert(sourcesList)
    }

    @Insert
    abstract suspend fun insert(sourcesDatabaseEntity: List<SourcesDatabaseEntity>)

    @Query("SELECT * FROM sources")
    abstract suspend fun getSources(): List<SourcesDatabaseEntity>

    @Query("DELETE FROM sources")
    abstract suspend fun delete()

}