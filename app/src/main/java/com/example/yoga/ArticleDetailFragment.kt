package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.yoga.databinding.FragmentArticleDetailBinding
import kotlinx.android.synthetic.main.fragment_article_detail.view.*


class ArticleDetailFragment() : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_article_detail, container, false)
        view.apply {
            wvDetailArticle.webViewClient = WebViewClient()
            wvDetailArticle.settings.javaScriptEnabled = true
            wvDetailArticle.settings.builtInZoomControls = true
            wvDetailArticle.loadUrl("")
        }

        return  view

//        val getLink = arguments?.getString("link")
//        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
//        binding.apply {
//
//            btnArticle.setOnClickListener {
//                findNavController().navigateUp()
//            }
//        }
//
//        return binding.root

    }
}