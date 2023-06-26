package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.json.UserModel

interface ArticlesRepository {
    suspend fun getArticles(): List<ArticleModel>

    suspend fun getUsers(userModel: UserModel): UserModel
}