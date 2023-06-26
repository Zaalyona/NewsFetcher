package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel
import com.example.newsfetcher.json.UserModel

class ArticlesRemoteSource(private val api: NewsApi) {

    suspend fun getArticles(): ArticlesRemoteModel {
        return api.getArticles()
    }

    //тест конвертирования json
    suspend fun getUsers(userModel: UserModel): UserModel {
        return api.postArticles(userModel)
    }
}