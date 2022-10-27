package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article.view.*

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_article, container, false)
        view.apply {
                rcvArticle.layoutManager = LinearLayoutManager(context)
                rcvArticle.adapter = ArticleAdapter(Article.getArticles(context).shuffled())
        }

        return  view

    }
}

