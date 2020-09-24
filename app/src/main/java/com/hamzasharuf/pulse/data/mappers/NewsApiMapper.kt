package com.hamzasharuf.pulse.data.mappers

import com.hamzasharuf.pulse.data.api.responses.NewsResponse
import com.hamzasharuf.pulse.data.api.responses.Response
import com.hamzasharuf.pulse.data.api.responses.Result
import com.hamzasharuf.pulse.data.api.responses.Tag
import com.hamzasharuf.pulse.data.models.News

object NewsApiMapper {

    fun mapToLocal(remote: NewsResponse): List<News> {
        val newsList = mutableListOf<News>()
        remote.response.results?.forEach {
            if (isValid(it)) {
                newsList.add(
                    News(
                        it.webTitle!!,
                        it.sectionName!!,
                        getAuthors(it.tags!!),
                        it.webPublicationDate!!,
                        it.webUrl!!,
                        it.fields!!.thumbnail!!,
                        it.fields.trailText!!,
                        it.fields.body!!
                    )
                )

            }
        }
        return newsList
    }

    private fun isValid(result: Result): Boolean {
        with(result) {
            if (webTitle.isNullOrBlank()) return@isValid false
            if (sectionName.isNullOrBlank()) return@isValid false
            if (tags == null || tags.isEmpty()) return@isValid false
            if (webPublicationDate.isNullOrBlank()) return@isValid false
            if (webUrl.isNullOrBlank()) return@isValid false
            if (fields?.thumbnail == null || fields.trailText == null) return@isValid false
            if (fields.body == null) return@isValid false
            return@isValid true
        }
    }


    private fun getAuthors(tags: List<Tag>): String {
        return buildString {
            tags.forEach {
                append("${it.webTitle}, ")
            }
        }.removeSuffix(", ")
    }
}