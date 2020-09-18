package com.hamzasharuf.pulse.data.repositories

import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.mappers.NewsApiMapper
import com.hamzasharuf.pulse.data.models.News
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
){

    suspend fun fetchAllNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getAllNews(request.page,request.pageSize))
    }

    suspend fun fetchWorldNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getWorldNews(request.page,request.pageSize))
    }

    suspend fun fetchScienceNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getScienceNews(request.page,request.pageSize))
    }

    suspend fun fetchSportNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getSportNews(request.page,request.pageSize))
    }

    suspend fun fetchEnvironmentNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getEnvironmentNews(request.page,request.pageSize))
    }

    suspend fun fetchSocietyNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getSocietyNews(request.page,request.pageSize))
    }

    suspend fun fetchFashionNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getFashionNews(request.page,request.pageSize))
    }

    suspend fun fetchBusinessNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getBusinessNews(request.page,request.pageSize))
    }

    suspend fun fetchCultureNews(request: NewsRequest): List<News>{
        return NewsApiMapper.mapToLocal(newsApi.getCultureNews(request.page,request.pageSize))
    }

}