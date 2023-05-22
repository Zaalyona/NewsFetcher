package com.example.newsfetcher.feature.article.ui

import android.content.Intent
import android.os.Bundle
import com.example.newsfetcher.R
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageView
import com.example.newsfetcher.MainActivity
import com.example.newsfetcher.feature.mainscreen.ArticlesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class ArticleActivity : AppCompatActivity() {

    private val tvArticleTitleTb: TextView by lazy { findViewById(R.id.tvArticleTitleTb) }
    private val newsPreviewImageView: AppCompatImageView by lazy {
        findViewById(R.id.newsPreviewImageView)
    }
    private val tvArticleDescription: TextView by lazy { findViewById(R.id.tvArticleDescription) }
    private val fabComeBack: FloatingActionButton by lazy { findViewById(R.id.fabComeBack) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        tvArticleTitleTb.text = ArticlesAdapter.articleTitle
        tvArticleDescription.text = ArticlesAdapter.articleDescription

        if (ArticlesAdapter.urlToImage != "") {
            Picasso.get()
                .load(ArticlesAdapter.urlToImage)
                .error(R.drawable.baseline_browser_not_supported_24)
                //.placeholder(R.drawable.placeholder)
                .fit()
                .into(newsPreviewImageView)
        }
        else newsPreviewImageView.setImageResource(R.drawable.baseline_browser_not_supported_24)

        fabComeBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}