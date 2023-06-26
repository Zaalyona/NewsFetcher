package com.example.newsfetcher.feature.article.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.example.newsfetcher.MainActivity
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.mainscreen.ArticlesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class ArticleFragment : Fragment(R.layout.fragment_article)  {

    private val tvArticleTitleTb: TextView by lazy { requireActivity().findViewById(R.id.tvArticleTitleTb) }
    private val newsPreviewImageView: AppCompatImageView by lazy {
        requireActivity().findViewById(R.id.newsPreviewImageView)
    }
    private val tvArticleDescription: TextView by lazy { requireActivity().findViewById(R.id.tvArticleDescription) }
    private val fabComeBack: FloatingActionButton by lazy { requireActivity().findViewById(R.id.fabComeBack) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvArticleTitleTb.text = ArticlesAdapter.articleTitle
        tvArticleDescription.text = ArticlesAdapter.articleDescription

        if (ArticlesAdapter.urlToImage != "") {
            Picasso.get()
                .load(ArticlesAdapter.urlToImage)
                .error(R.drawable.baseline_error_24)
                .placeholder(R.drawable.baseline_article_24)
                .fit()
                .into(newsPreviewImageView)
        }
        else newsPreviewImageView.setImageResource(R.drawable.baseline_error_24)

        fabComeBack.setOnClickListener {
            parentFragmentManager.beginTransaction().detach(this).commit()
        }
    }
}