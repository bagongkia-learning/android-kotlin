package com.bca.learning.newsapp.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.bca.learning.newsapp.api.IN_QUALIFIER
import com.bca.learning.newsapp.api.NewsService
import com.bca.learning.newsapp.model.Article
import com.bca.learning.newsapp.model.NewsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsService: NewsService) {

    fun getSearchNews(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsService, query)}
        ).liveData

}