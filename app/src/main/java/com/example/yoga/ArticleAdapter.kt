package com.example.yoga

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_view.view.*

class ArticleAdapter(private val articleList: List<Article>): RecyclerView.Adapter<ArticleHolder>() {

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

            val intent = Intent(holder.view.context, ArticleDetail::class.java)
            intent.putExtra("link", articleList[position].link)
            holder.view.context.startActivity(intent)
//            val bundle = Bundle()
//            bundle.putString("link", articleList[position].link)
           // findNavController.navigate(R.id.action_articleFragment_to_articleDetailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

}

class ArticleHolder(val view: View): RecyclerView.ViewHolder(view) {
}