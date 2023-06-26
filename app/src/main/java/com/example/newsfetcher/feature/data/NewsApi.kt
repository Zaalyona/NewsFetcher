package com.example.newsfetcher.feature.data

import com.example.newsfetcher.SampleModel
import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.newsfetcher.di.API_KEY
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        //@Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String = "ru",
    ) : ArticlesRemoteModel

    //Формирование запроса
    @POST("v2/top-headlines")
    suspend fun postArticles(
        @Body sampleModel: SampleModel = SampleModel("ru", 5, listOf("123", "1234567"))
    ) : ArticlesRemoteModel
}