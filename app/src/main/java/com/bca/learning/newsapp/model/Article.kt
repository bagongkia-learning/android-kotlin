package com.bca.learning.newsapp.model

import com.google.gson.annotations.SerializedName

data class Article(
    @field:SerializedName("author") val author: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("url") val url: String
)