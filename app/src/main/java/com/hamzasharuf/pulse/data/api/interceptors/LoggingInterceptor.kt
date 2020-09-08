package com.hamzasharuf.pulse.data.api.interceptors

import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor {

    companion object{

        fun getBodyLevelInterceptor(): HttpLoggingInterceptor{
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        fun getHeadersLevelInterceptor(): HttpLoggingInterceptor{
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            }
        }

    }

}