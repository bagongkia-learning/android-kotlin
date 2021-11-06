package com.bca.learning.newsapp.api

import com.bca.learning.newsapp.model.News
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:title"

interface NewsService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "5c52b44b80c8406f883ba43c663bc519"
    }

    @GET("everything?sortBy=publishedAt&apiKey=$API_KEY&language=en")
    suspend fun searchNews (
        @Query("q") query: String,
        @Query("page") page: Int
    ): News

}