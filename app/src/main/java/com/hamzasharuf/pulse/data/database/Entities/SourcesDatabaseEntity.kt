package com.hamzasharuf.pulse.data.database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hamzasharuf.pulse.data.database.Entities.SourcesDatabaseEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class SourcesDatabaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val category: String?, // general
    val country: String?, // us
    val description: String?, // Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.
    val newsId: String?, // abc-news
    val language: String?, // en
    val name: String?, // ABC News
    val url: String? // https://abcnews.go.com
) {
    companion object {
        const val TABLE_NAME = "sources"
    }
}