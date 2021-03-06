package com.bca.learning.newsapp.api

import com.bca.learning.newsapp.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    companion object {
        const val BASE_URL = "https://talentpool.oneindonesia.id/api/"
        const val API_KEY = "454041184B0240FBA3AACD15A1F7A8BB"
    }

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Header("X-API-KEY") apiKey: String,
        @Field("username") username: String,
        @Field("password") password: String
    ) : LoginResponse
}