package com.hamzasharuf.pulse.data.database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hamzasharuf.pulse.data.database.Entities.NewsDatabaseEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NewsDatabaseEntity(
    val title: String,
    val section: String,
    val authors: String?,
    val date: String,
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val thumbnail: String,
    val trailTextHtml: String,
    val sectionName: String,
){
    companion object{
        const val TABLE_NAME = "news"
    }
}