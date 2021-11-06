package com.bca.learning.newsapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.bca.learning.newsapp.R
import com.bca.learning.newsapp.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel by viewModels<NewsViewModel>()
    private var _binding : FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewsBinding.bind(view)

        val adapter = NewsAdapter()

        binding.apply {
            rvNews.setHasFixedSize(true)
            rvNews.adapter = adapter
            btnTryAgain.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.news.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvNews.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible =loadState.source.refresh is LoadState.Error
                newsFailed.isVisible = loadState.source.refresh is LoadState.Error

                //not found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1){
                    rvNews.isVisible = false
                    newsNotFound.isVisible = true
                } else {
                    newsNotFound.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    binding.rvNews.scrollToPosition(0)
                    viewModel.searchNews(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}