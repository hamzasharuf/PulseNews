package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.data.api.responses.source.SourceInfoResponse
import com.hamzasharuf.pulse.data.api.responses.source.SourcesResponse
import javax.inject.Inject

class NewsSourceNetworkMapper @Inject constructor() : EntityMapper<List<NewsSource>, SourcesResponse> {
    override fun mapFromEntity(entity: List<NewsSource>): SourcesResponse {
        val sources = mutableListOf<SourceInfoResponse>()
        entity.forEach {
            sources.add(
                SourceInfoResponse(
                    it.category,
                    it.country,
                    it.description,
                    it.id,
                    it.language,
                    it.name,
                    it.url
                )
            )
        }
        return SourcesResponse(sources, "ok")
    }

    override fun mapToEntity(domainModel: SourcesResponse): List<NewsSource> {
        val mList = mutableListOf<NewsSource>()
        domainModel.sources?.forEach {
            mList.add(
                NewsSource(
                    it.category,
                    it.country,
                    it.description,
                    it.id,
                    it.language,
                    it.name,
                    it.url
                )
            )
        }
        return mList
    }

}