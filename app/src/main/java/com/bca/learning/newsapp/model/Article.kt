package com.bca.learning.newsapp.model

import com.google.gson.annotations.SerializedName

data class Article(
    @field:SerializedName("title") val title: String
)