package com.hamzasharuf.pulse.data.repositories

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.api.requests.SourcesRequest
import com.hamzasharuf.pulse.data.database.Dao.NewsDao
import com.hamzasharuf.pulse.data.mappers.ArticleDatabaseMapper
import com.hamzasharuf.pulse.data.mappers.ArticleNetworkMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceNetworkMapper
import com.hamzasharuf.pulse.data.models.Article
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.utils.Exceptions.NoInternetException
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
    private val newsSourceNetworkMapper: NewsSourceNetworkMapper,
    private val articleNetworkMapper: ArticleNetworkMapper,
    private val articleDatabaseMapper: ArticleDatabaseMapper,
) : NewsRepository {

    override suspend fun getNewsSources(request: SourcesRequest): Flow<Resource<List<NewsSource>>> =
        flow {
            emit(Resource.loading(null))
            runCatching {
                val networkSources = newsApi.getSources(request.toMap())
                val sources = newsSourceNetworkMapper.mapToEntity(networkSources)
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

                // Fetch News From Api
                val latestNews = fetchNewsFromApi(request)

                // Cache in database
                val dbNews = updateCachedNews(latestNews)

                // Emit to the view
                emit(Resource.success(dbNews))

            }.onFailure {
                when (it) {
                    is NoInternetException -> emit(Resource.error(it.message.toString(), null))
                    is Exception -> emit(Resource.error(it.message.toString(), null))
                }
            }
        }


    private suspend fun fetchNewsFromApi(request: NewsRequest): List<Article> {
        val networkLatestNews = newsApi.getLatestNews(request.toMap())
        return articleNetworkMapper.mapToEntityList(networkLatestNews).take(50)
    }

    private suspend fun updateCachedNews(newsList: List<Article>): List<Article> {
        newsDao.updateNews(articleDatabaseMapper.mapFromEntityList(newsList))
        return articleDatabaseMapper.mapToEntityList(newsDao.getNews())
    }


}