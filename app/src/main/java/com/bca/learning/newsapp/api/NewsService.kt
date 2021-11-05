package com.bca.learning.newsapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/everything?sortBy=publishedAt")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("date") date: String
    ): NewsResponse



}