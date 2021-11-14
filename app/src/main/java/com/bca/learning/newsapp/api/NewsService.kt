package com.bca.learning.newsapp.api

import com.bca.learning.newsapp.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "04e58a96037547ad934a61be34f20f1f"
    }

    @GET("everything?sortBy=publishedAt&apiKey=$API_KEY&language=en")
    suspend fun searchNews (
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): News

}