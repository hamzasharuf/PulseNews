package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.database.Entities.NewsDatabaseEntity
import com.hamzasharuf.pulse.data.models.News

object NewsDatabaseMapper {

    fun mapToLocal(entity: NewsDatabaseEntity): News =
        News(
            entity.title,
            entity.section,
            entity.authors,
            entity.date,
            entity.url,
            entity.thumbnail,
            entity.trailTextHtml,
            entity.articleBody
        )

    fun mapFromLocal(sectionName: String, news: News) =
        NewsDatabaseEntity(
            news.title,
            news.section,
            news.authors,
            news.date,
            news.url,
            news.thumbnail,
            news.trailTextHtml,
            sectionName,
            news.articleBody
        )


    fun mapFromLocalList(newsList: List<News>, sectionName: String) =
        newsList.map { mapFromLocal(sectionName, it) }


    fun mapToLocalList(entities: List<NewsDatabaseEntity>): List<News> =
        entities.map { mapToLocal(it) }
}