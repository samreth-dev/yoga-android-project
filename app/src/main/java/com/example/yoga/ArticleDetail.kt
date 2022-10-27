package com.example.yoga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yoga.R
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetail() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wvDetailArticle.loadUrl(intent.getStringExtra("link")?:"")
        btnArticle.setOnClickListener {
            finish()
        }

    }
}