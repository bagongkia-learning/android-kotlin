package com.bca.learning.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bca.learning.newsapp.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var binding: FragmentArticleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentArticleBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }
}