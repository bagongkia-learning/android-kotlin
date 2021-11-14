package com.bca.learning.newsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bca.learning.newsapp.api.NewsService
import com.bca.learning.newsapp.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsService: NewsService) {

    fun getSearchNewsStream(query: String): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsService, query)}
        ).flow

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}