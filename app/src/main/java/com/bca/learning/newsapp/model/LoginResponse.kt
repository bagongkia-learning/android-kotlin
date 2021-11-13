package com.bca.learning.newsapp.model

data class LoginResponse (
    val status: Boolean,
    val message: String,
    val token: String?
)