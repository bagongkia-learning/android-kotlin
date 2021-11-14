package com.bca.learning.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bca.learning.newsapp.R
import com.bca.learning.newsapp.databinding.ItemArticleBinding
import com.bca.learning.newsapp.model.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var article: Article? = null
    private var _binding : ItemArticleBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = ItemArticleBinding.bind(view)

        view.setOnClickListener {
            if (article != null) {
                val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(article!!)
                Navigation.findNavController(view).navigate(action)
            }
        }

    }

    fun bind(article: Article?) {
        if (article != null) {
            showArticle(article)
        }
    }

    private fun showArticle(article: Article) {
        this.article = article
        binding.apply {
            articleTitle.text = article.title
            Glide.with(itemView)
                .load("${article.urlToImage}")
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(articleImage)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false)
            return NewsViewHolder(view)
        }
    }
}