package com.example.newsfetcher.feature.data.model

import android.icu.text.CaseMap.Title
import com.google.gson.annotations.SerializedName

data class ArticleRemoteModel (

    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String
)