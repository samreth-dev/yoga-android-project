package com.example.yoga

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yoga.databinding.FragmentArticleBinding
import kotlinx.android.synthetic.main.article_view.view.*
import kotlinx.android.synthetic.main.fragment_article.view.*
import kotlinx.android.synthetic.main.fragment_article_detail.view.*

class ArticleAdapter(private val articleList: List<Article>, private var findNavController: NavController): RecyclerView.Adapter<ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.article_view, parent, false)
        return ArticleHolder(listItem)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articleList[position]
        holder.view.tvArticleTitle.text = article.title
        holder.view.tvArticleSource.text = "by: " + article.source
        holder.view.cvArticle.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("link", articleList[position].link)
            findNavController.navigate(R.id.action_articleFragment_to_articleDetailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

}

class ArticleHolder(val view: View): RecyclerView.ViewHolder(view) {
}