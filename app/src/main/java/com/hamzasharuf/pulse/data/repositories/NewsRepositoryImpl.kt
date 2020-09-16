package com.hamzasharuf.pulse.data.repositories

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.api.requests.SourcesRequest
import com.hamzasharuf.pulse.data.database.Dao.NewsDao
import com.hamzasharuf.pulse.data.database.Dao.SourcesDao
import com.hamzasharuf.pulse.data.mappers.ArticleDatabaseMapper
import com.hamzasharuf.pulse.data.mappers.ArticleNetworkMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceDatabaseMapper
import com.hamzasharuf.pulse.data.mappers.NewsSourceNetworkMapper
import com.hamzasharuf.pulse.data.models.Article
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.utils.exceptions.NoInternetException
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
    private val sourcesDao: SourcesDao,
    private val newsSourceNetworkMapper: NewsSourceNetworkMapper,
    private val newsSourceDatabaseMapper: NewsSourceDatabaseMapper,
    private val articleNetworkMapper: ArticleNetworkMapper,
    private val articleDatabaseMapper: ArticleDatabaseMapper,
) : NewsRepository {

    override suspend fun getNewsSources(request: SourcesRequest): Flow<Resource<List<NewsSource>>> =
        flow {
            emit(Resource.loading(null))
            runCatching {

                // Fetch available sources from api
                val apiSources = fetchSourcesFromApi(request)

                // Cache in database
                val cachedSources = cacheSources(apiSources)

                // Emit to the view
                emit(Resource.success(cachedSources))

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

                // Fetch news from api
                val latestNews = fetchNewsFromApi(request)

                // Cache in database [No need to cache news (For now)]
                //val dbNews = updateCachedNews(latestNews)

                // Emit to the view
                emit(Resource.success(latestNews))

            }.onFailure {
                when (it) {
                    is NoInternetException -> emit(Resource.error(it.message.toString(), null))
                    is Exception -> emit(Resource.error(it.message.toString(), null))
                }
            }
        }

    private suspend fun fetchSourcesFromApi(request: SourcesRequest): List<NewsSource>{
        val networkSources = newsApi.getSources(request.toMap())
        return newsSourceNetworkMapper.mapToEntity(networkSources)
    }

    private suspend fun cacheSources(sourcesList: List<NewsSource>): List<NewsSource>{
        sourcesDao.update(newsSourceDatabaseMapper.mapFromEntityList(sourcesList))
        return newsSourceDatabaseMapper.mapToEntityList(sourcesDao.getSources())
    }

    private suspend fun fetchNewsFromApi(request: NewsRequest): List<Article> {
        val networkLatestNews = newsApi.getLatestNews(request.toMap())
        return articleNetworkMapper.mapToEntityList(networkLatestNews).take(50)
    }

    private suspend fun updateCachedNews(newsList: List<Article>): List<Article> {
        newsDao.update(articleDatabaseMapper.mapFromEntityList(newsList))
        return articleDatabaseMapper.mapToEntityList(newsDao.getNews())
    }


}