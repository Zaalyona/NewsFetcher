package com.example.newsfetcher.feature.mainscreen

import androidx.fragment.app.Fragment
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

enum class State {
    Load,
    Content,
    Error
}

data class ViewState(
    val state: State,
    val isSearchEnabled: Boolean,
    val articlesShown: List<ArticleModel>,
    val articleList: List<ArticleModel>
)

sealed class UiEvent() : Event {
    data class OnArticleClicked(val fragment: Fragment) : UiEvent()
    object OnSearchButtonClicked : UiEvent()
    data class OnSearchEdit(val text: String) : UiEvent()
}

sealed class DataEvent() : Event {

    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
}

