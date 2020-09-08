package com.hamzasharuf.pulse.data.repositories

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.models.Article
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.api.requests.SourcesRequest
import com.hamzasharuf.pulse.data.mappers.ArticlesMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceMapper
import com.hamzasharuf.pulse.utils.Exceptions.NoInternetException
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val newsApi: NewsApi,
    private val newsSourceMapper: NewsSourceMapper,
    private val articlesMapper: ArticlesMapper
) : NewsRepository {

    override suspend fun getNewsSources(request: SourcesRequest): Flow<Resource<List<NewsSource>>> =
        flow {
            emit(Resource.loading(null))
            runCatching {
                val networkSources = newsApi.getSources(request.toMap())
                val sources = newsSourceMapper.mapToEntity(networkSources)
                // Cache in database
                emit(Resource.success(sources))
            }.onFailure {
                when (it) {
                    is NoInternetException -> emit(Resource.error(it.message.toString(), null))
                    is Exception -> emit(Resource.error(it.message.toString(), null))
                }
            }
        }

    override suspend fun getNews(request: NewsRequest): Flow<Resource<List<Article>>> =
        flow {
            emit(Resource.loading(null))
            runCatching {
                val networkLatestNews = newsApi.getLatestNews(request.toMap())
                val latestNews = articlesMapper.mapToEntityList(networkLatestNews).take(50)
                // Cache in database
                emit(Resource.success(latestNews))
            }.onFailure {
                when (it) {
                    is NoInternetException -> emit(Resource.error(it.message.toString(), null))
                    is Exception -> emit(Resource.error(it.message.toString(), null))
                }
            }
        }


}