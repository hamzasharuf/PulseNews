package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.api.responses.NewsResponse
import com.hamzasharuf.pulse.data.api.responses.Tag
import com.hamzasharuf.pulse.data.models.News

object NewsApiMapper {

    fun mapToLocal(remote: NewsResponse): List<News> {
        val newsList = mutableListOf<News>()

        remote.response.results.forEach {
            newsList.add(
                News(
                    it.webTitle,
                    it.sectionName,
                    getAuthors(it.tags),
                    it.webPublicationDate,
                    it.webUrl,
                    it.fields.thumbnail,
                    it.fields.trailText,
                )
            )
        }
        return newsList
    }


    private fun getAuthors(tags: List<Tag>): String {
        return buildString {
            tags.forEach {
                append("${it.webTitle}, ")
            }
        }.removeSuffix(", ")
    }
}