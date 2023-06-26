package com.example.newsfetcher.feature.domain

import android.util.Log
import com.example.newsfetcher.SampleModel
import com.example.newsfetcher.base.attempt
import com.example.newsfetcher.feature.data.ArticlesRepository
import com.google.gson.Gson
import com.google.gson.JsonObject

class ArticlesInteractor(private val repository: ArticlesRepository) {

    //{"count":5,"country":"ru","list":["123","1234567"]}
    suspend fun getArticles() = attempt {

        //С gson в Json
        val gson = Gson()
        val request = gson.toJson(JsonObject().apply {
            addProperty("count", 5)
            addProperty("country", "ru")
            addProperty("list", gson.toJson(listOf("123","1234567")))
        })

        Log.e("LOG -----------", request)

        //С Json в gson
        val response = "{\"count\":5,\"country\":\"ru\",\"list\":[\"123\",\"1234567\"]}"
        val model = gson.fromJson(response, SampleModel::class.java)

        Log.e("LOG -----------", model.toString())

        repository.getArticles() }
}