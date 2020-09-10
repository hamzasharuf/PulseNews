package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.api.responses.everything.ArticleResponse
import com.hamzasharuf.pulse.data.api.responses.everything.EverythingResponse
import com.hamzasharuf.pulse.data.api.responses.everything.SourceResponse
import com.hamzasharuf.pulse.data.models.Article
import javax.inject.Inject

class ArticleNetworkMapper @Inject constructor() : EntityMapper<Article, ArticleResponse> {

    override fun mapFromEntity(entity: Article): ArticleResponse =
        ArticleResponse(
            entity.author,
            entity.content,
            entity.description,
            entity.publishedAt,
            SourceResponse(entity.source_id, entity.source_name),
            entity.title,
            entity.url,
            entity.urlToImage,
        )


    override fun mapToEntity(domainModel: ArticleResponse): Article {
        return Article(
            domainModel.author,
            domainModel.content,
            domainModel.description,
            domainModel.publishedAt,
            domainModel.source?.id,
            domainModel.source?.name,
            domainModel.title,
            domainModel.url,
            domainModel.urlToImage,
        )
    }

    fun mapToEntityList(domainModel: EverythingResponse): List<Article> =
        domainModel.articles.map { mapToEntity(it) }


    companion object {
        private const val TAG = "ArticlesNetworkMapper"
    }
}