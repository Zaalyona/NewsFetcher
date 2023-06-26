package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.json.UserModel

class ArticlesRepositoryImpl(private val source: ArticlesRemoteSource) : ArticlesRepository {

    override suspend fun getArticles(): List<ArticleModel> {
        return source.getArticles().articleList.map {
            it.toDomain()
        }.sortedBy { it.publishedAt }
    }

    override suspend fun getUsers(userModel: UserModel): UserModel {
        return source.getUsers(userModel)
    }


}