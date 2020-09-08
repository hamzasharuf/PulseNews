package com.hamzasharuf.pulse.data.api.responses.everything

data class EverythingResponse(

    val articles: List<ArticleResponse>,
    val status: String, // ok
    val totalResults: Int // 5377
)