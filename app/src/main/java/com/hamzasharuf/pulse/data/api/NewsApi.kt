package com.hamzasharuf.pulse.data.api

import com.hamzasharuf.pulse.data.api.responses.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("search")
    suspend fun getAllNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
    ): NewsResponse

    @GET("search")
    suspend fun getWorldNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "world",
    ): NewsResponse

    @GET("search")
    suspend fun getScienceNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "science",
    ): NewsResponse

    @GET("search")
    suspend fun getSportNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "sport",
    ): NewsResponse

    @GET("search")
    suspend fun getEnvironmentNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "environment",
    ): NewsResponse

    @GET("search")
    suspend fun getSocietyNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "society",
    ): NewsResponse

    @GET("search")
    suspend fun getFashionNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "fashion",
    ): NewsResponse

    @GET("search")
    suspend fun getBusinessNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "business",
    ): NewsResponse

    @GET("search")
    suspend fun getCultureNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String = "culture",
    ): NewsResponse


}