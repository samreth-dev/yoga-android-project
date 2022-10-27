package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_article_detail.view.*


class ArticleDetailFragment() : Fragment() {

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

    }
}