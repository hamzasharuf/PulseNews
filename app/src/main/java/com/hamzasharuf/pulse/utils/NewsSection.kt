package com.hamzasharuf.pulse.utils

enum class NewsSection(val page: Int, val sectionName: String) {
    HOME(0, "home"),
    WORLD(1, "world"),
    SCIENCE(2, "science"),
    SPORT(3, "sport"),
    ENVIRONMENT(4, "environment"),
    SOCIETY(5, "society"),
    FASHION(6, "fashion"),
    BUSINESS(7, "business"),
    CULTURE(8, "culture");

    companion object {
        fun getItem(position: Int): NewsSection = values()[position]
    }

}