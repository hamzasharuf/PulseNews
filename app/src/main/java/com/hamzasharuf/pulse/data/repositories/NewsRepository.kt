package com.hamzasharuf.pulse.data.repositories

import com.hamzasharuf.pulse.data.models.Article
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.api.requests.SourcesRequest
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsSources(request: SourcesRequest): Flow<Resource<List<NewsSource>>>
    suspend fun getNews(request: NewsRequest): Flow<Resource<List<Article>>>
}