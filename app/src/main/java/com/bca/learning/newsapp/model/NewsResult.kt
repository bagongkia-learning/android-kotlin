package com.bca.learning.newsapp.model

import java.lang.Exception

sealed class NewsResult {
    data class Success(val data: List<Article>) : NewsResult()
    data class Error(val error: Exception) : NewsResult()
}