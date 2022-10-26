package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yoga.databinding.ArticleViewBinding
import com.example.yoga.databinding.FragmentArticleBinding
import com.example.yoga.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val getLink = arguments?.getString("link")
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        binding.apply {
            wvDetailArticle.webViewClient = WebViewClient()
            wvDetailArticle.settings.javaScriptEnabled = true
            wvDetailArticle.settings.builtInZoomControls = true
            wvDetailArticle.loadUrl(getLink?:"https://www.google.com")
            btnArticle.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        return binding.root

    }

}