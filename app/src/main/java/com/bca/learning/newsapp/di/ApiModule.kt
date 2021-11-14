package com.bca.learning.newsapp.di

import android.app.Application
import com.bca.learning.newsapp.api.LoginService
import com.bca.learning.newsapp.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Qualifier
    annotation class NewsRetrofit

    @Qualifier
    annotation class LoginRetrofit

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @NewsRetrofit
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(NewsService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNewsService(@NewsRetrofit retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Provides
    @LoginRetrofit
    @Singleton
    fun provideLoginRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(LoginService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideLoginService(@LoginRetrofit retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

}