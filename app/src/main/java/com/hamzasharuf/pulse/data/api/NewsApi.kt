package com.hamzasharuf.pulse.data.api

import com.hamzasharuf.pulse.data.api.responses.everything.EverythingResponse
import com.hamzasharuf.pulse.data.api.responses.source.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("sources")
    suspend fun getSources(@QueryMap request: Map<String, String>) : SourcesResponse

    @GET("everything")
    suspend fun getLatestNews(@QueryMap request: Map<String, String>): EverythingResponse

}