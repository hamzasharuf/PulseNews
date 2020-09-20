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
    suspend fun getSectionNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-tags") showTags: String = "contributor",
        @Query("show-fields") showFields: String = "all",
        @Query("section") section: String,
    ): NewsResponse

}