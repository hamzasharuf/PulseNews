package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.api.responses.everything.EverythingResponse
import com.hamzasharuf.pulse.data.api.responses.everything.SourceResponse
import com.hamzasharuf.pulse.data.database.Entities.ArticleDatabaseEntity
import com.hamzasharuf.pulse.data.models.Article
import javax.inject.Inject

class ArticleDatabaseMapper @Inject constructor() : EntityMapper<Article, ArticleDatabaseEntity> {

    override fun mapFromEntity(entity: Article): ArticleDatabaseEntity =
        ArticleDatabaseEntity(
            0,
            entity.author,
            entity.content,
            entity.description,
            entity.publishedAt,
            entity.source_id,
            entity.source_name,
            entity.title,
            entity.url,
            entity.urlToImage,
        )


    override fun mapToEntity(domainModel: ArticleDatabaseEntity): Article {
        return Article(
            domainModel.author,
            domainModel.content,
            domainModel.description,
            domainModel.publishedAt,
            domainModel.source_id,
            domainModel.source_name,
            domainModel.title,
            domainModel.url,
            domainModel.urlToImage,
        )
    }

    fun mapToEntityList(domainModel: List<ArticleDatabaseEntity>): List<Article> =
        domainModel.map { mapToEntity(it) }

    fun mapFromEntityList(articlesList: List<Article>): List<ArticleDatabaseEntity> =
        articlesList.map { mapFromEntity(it) }




    companion object {
        private const val TAG = "ArticlesDatabaseMapper"
    }
}