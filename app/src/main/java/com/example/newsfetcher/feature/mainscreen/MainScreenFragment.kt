package com.example.newsfetcher.feature.mainscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.article.ui.ArticleFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArcticles) }
    private val ivSearch: ImageView by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val tvTitle: TextView by lazy { requireActivity().findViewById(R.id.tvTitle) }
    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }

    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter { index ->
            viewModel.processUiEvent(UiEvent.OnArticleClicked(index))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter

        ivSearch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSearchButtonClicked)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.processUiEvent(UiEvent.OnSearchEdit(text.toString()))
            }

        })

        adapter.setOnItemClickListener(object : ArticlesAdapter.onItemClickListener {

            override fun onItemClick(position: Int) {
                val intent = Intent(activity!!.applicationContext, ArticleFragment::class.java)
                intent.putExtra("position", position.toString())

                parentFragmentManager.beginTransaction().replace(android.R.id.content, ArticleFragment())
                    .commit()
            }

        })
    }

    /*override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)

        adapter.setOnItemClickListener(object : ArticlesAdapter.onItemClickListener {

            override fun onItemClick(position: Int) {
                val intent = Intent(activity!!.applicationContext, ArticleFragment::class.java)
                intent.putExtra("position", position.toString())

                parentFragmentManager.beginTransaction().replace(android.R.id.content, ArticleFragment())
                    .commit()
            }

        })
    }*/

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.setOnItemClickListener(object : ArticlesAdapter.onItemClickListener {

            override fun onItemClick(position: Int) {
                val intent = Intent(activity!!.applicationContext, ArticleFragment::class.java)
                intent.putExtra("position", position.toString())

                parentFragmentManager.beginTransaction().replace(android.R.id.content, ArticleFragment())
                    .commit()
            }

        })
    }*/

    private fun render(viewState: ViewState) {
        tvTitle.isVisible = !viewState.isSearchEnabled
        etSearch.isVisible = viewState.isSearchEnabled
        adapter.setData(viewState.articlesShown)
    }


}