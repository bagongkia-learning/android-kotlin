package com.bca.learning.newsapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bca.learning.newsapp.data.NewsRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val news = currentQuery.switchMap { query ->
        if (!query.isEmpty()) {
            repository.getSearchNews(query)
        } else {
            repository.getSearchNews("TRENDING")
        }
    }

    fun searchNews(query: String) {
        currentQuery.value = query
    }
}