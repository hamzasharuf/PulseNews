package com.hamzasharuf.pulse.data.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.hamzasharuf.pulse.data.api.BitmapLoaderApi
import com.hamzasharuf.pulse.data.api.NewsApi
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.database.AppDatabase
import com.hamzasharuf.pulse.data.mappers.NewsApiMapper
import com.hamzasharuf.pulse.data.mappers.NewsDatabaseMapper
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.utils.common.CommonFunctions.byteArrayToBitmap
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val bitmapLoaderApi: BitmapLoaderApi,
    private val database: AppDatabase,
){

    // Fetch the data from the server

    suspend fun fetchNews(request: NewsRequest, section: NewsSection): List<News> =
        when(section){
            NewsSection.HOME -> NewsApiMapper.mapToLocal(newsApi.getAllNews(request.page,request.pageSize))
            else -> NewsApiMapper.mapToLocal(newsApi.getSectionNews(request.page,request.pageSize, section = section.sectionName))
        }


    // Insert into database

    suspend fun insertNews(newsList: List<News>, section: NewsSection) =
        database.newsDao.insert(NewsDatabaseMapper.mapFromLocalList(newsList, section.sectionName))


    // Update the database

    suspend fun updateNews(newsList: List<News>, section: NewsSection) =
        database.newsDao.update(NewsDatabaseMapper.mapFromLocalList(newsList, section.sectionName), section.sectionName)


    // Read from the database

    suspend fun getNews(sectionName: String): List<News> =
        NewsDatabaseMapper.mapToLocalList(database.newsDao.getNews(sectionName))


    // Get Single item from the database
    suspend fun getSingleItem(section: NewsSection) = database.newsDao.getSingleItem(section.sectionName)

    suspend fun getBitmap(url:String): Bitmap{
        val bytes = bitmapLoaderApi.getImageData(url).bytes()
        return byteArrayToBitmap(bytes)
    }


}