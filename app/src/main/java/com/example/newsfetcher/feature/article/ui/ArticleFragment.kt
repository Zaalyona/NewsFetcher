package com.example.newsfetcher.feature.article.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsfetcher.R
import android.content.Intent

class ArticleFragment : Fragment(R.layout.fragment_article) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireActivity().intent.extras?.getString("position")?.toInt()

        if (position != null) {

        }
    }
}