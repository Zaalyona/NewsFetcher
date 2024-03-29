package com.example.newsfetcher.feature.bookmarks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsfetcher.R
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
    }
}