package com.hamzasharuf.pulse.data.models

data class News(
    val title: String,
    val section: String,
    val authors: String?,
    val date: String,
    val url: String,
    val thumbnail: String,
    val trailTextHtml: String,
)