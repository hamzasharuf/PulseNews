package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.database.Entities.SourcesDatabaseEntity
import com.hamzasharuf.pulse.data.models.NewsSource
import javax.inject.Inject

class NewsSourceDatabaseMapper @Inject constructor() : EntityMapper<NewsSource, SourcesDatabaseEntity> {
    override fun mapFromEntity(entity: NewsSource): SourcesDatabaseEntity =
        SourcesDatabaseEntity(
            0,
            entity.category,
            entity.country,
            entity.description,
            entity.id,
            entity.language,
            entity.name,
            entity.url
        )

    override fun mapToEntity(domainModel: SourcesDatabaseEntity): NewsSource =
        NewsSource(
            domainModel.category,
            domainModel.country,
            domainModel.description,
            domainModel.newsId,
            domainModel.language,
            domainModel.name,
            domainModel.url
        )

    fun mapToEntityList(domainModel: List<SourcesDatabaseEntity>): List<NewsSource> =
        domainModel.map { mapToEntity(it) }

    fun mapFromEntityList(sourcesList: List<NewsSource>): List<SourcesDatabaseEntity> =
        sourcesList.map { mapFromEntity(it) }
}