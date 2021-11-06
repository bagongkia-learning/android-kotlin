package com.bca.learning.newsapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bca.learning.newsapp.api.NewsService
import com.bca.learning.newsapp.model.Article

private const val STARTING_PAGE_INDEX = 1

class NewsPagingSource (
    private val newsService: NewsService,
    private val query: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val news = newsService.searchNews(query, position)
        return LoadResult.Page(
            data = news.articles,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (news.articles.isEmpty()) null else position + 1
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}