package com.hamzasharuf.pulse.data.api.responses


import com.google.gson.annotations.SerializedName

data class Response(
    val currentPage: Int, // 5
    val orderBy: String, // newest
    val pageSize: Int, // 10
    val pages: Int, // 221862
    val results: List<Result>,
    val startIndex: Int, // 41
    val status: String, // ok
    val total: Int, // 2218615
    val userTier: String // developer
)