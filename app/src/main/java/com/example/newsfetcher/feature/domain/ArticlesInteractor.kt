package com.example.newsfetcher.feature.domain

import android.util.Log
import com.example.newsfetcher.SampleModel
import com.example.newsfetcher.base.attempt
import com.example.newsfetcher.feature.data.ArticlesRepository
import com.example.newsfetcher.json.UserModel
import com.google.gson.Gson
import com.google.gson.JsonObject

class ArticlesInteractor(private val repository: ArticlesRepository) {

    //{"count":5,"country":"ru","list":["123","1234567"]}
    suspend fun getArticles() = attempt { repository.getArticles() }

    suspend fun getUsers() = attempt {

        val jsonString = """
            [
            {
            "id": "6212577f0095c22f40b1a78a",
            "email": "fields_tyson@manglo.degree",
            "roles": [
            "owner",
            "guest"
            ],
            "apiKey": "f767f1e7-63e2-4f7b-984d-1f4743e7dfd1",
            "profile": {
            "dob": "1989-02-17",
            "name": "Fields Tyson",
            "about": "Quis labore commodo culpa aliquip cillum deserunt culpa non pariatur minim ullamco reprehenderit nulla esse. Ullamco aliquip do commodo cillum.",
            "address": "59 Menahan Street, Loretto, Washington",
            "company": "Manglo",
            "location": {
            "lat": 56.740646,
            "long": -60.715809
            }
            },
            "username": "fields89",
            "createdAt": "2013-12-09T14:53:16.548Z",
            "updatedAt": "2013-12-10T14:53:16.548Z"
            }
            ]
        """.trimIndent()

        val userFromJson = Gson().fromJson(jsonString, UserModel::class.java)

        Log.d("USER FROM JSON", userFromJson.toString())

        val jsonFromUser = Gson().toJson(userFromJson)

        Log.d("JSON FROM USER", jsonFromUser.toString())

    }

        //С gson в Json
        /*val gson = Gson()
        val request = gson.toJson(JsonObject().apply {
            addProperty("count", 5)
            addProperty("country", "ru")
            addProperty("list", gson.toJson(listOf("123","1234567")))
        })

        Log.e("LOG -----------", request)

        //С Json в gson
        val response = "{\"count\":5,\"country\":\"ru\",\"list\":[\"123\",\"1234567\"]}"
        val model = gson.fromJson(response, SampleModel::class.java)

        Log.e("LOG -----------", model.toString())*/




}