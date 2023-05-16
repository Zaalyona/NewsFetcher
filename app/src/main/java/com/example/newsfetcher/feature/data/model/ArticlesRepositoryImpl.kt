package com.example.newsfetcher.feature.data.model

import com.example.newsfetcher.feature.data.ArticlesRemoteSource
import com.example.newsfetcher.feature.data.ArticlesRepository
import com.example.newsfetcher.feature.data.toDomain
import com.example.newsfetcher.feature.domain.ArticleModel
import java.util.PrimitiveIterator

class ArticlesRepositoryImpl(private val source: ArticlesRemoteSource) : ArticlesRepository {

    override suspend fun getArticles(): List<ArticleModel> {
        return source.getArticles().articleList.map {
            it.toDomain()
        }.sortedBy { it.publishedAt }
    }
}