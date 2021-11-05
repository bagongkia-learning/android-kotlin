package com.bca.learning.newsapp.api

import com.bca.learning.newsapp.model.Article
import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("totalResults") val totalResults: Int = 0,
    @SerializedName("articles") val articles: List<Article> = emptyList(),
    val date: String? = null
)