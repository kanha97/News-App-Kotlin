package com.devkanhaiya.newsapp.API

data class NewsGsonApi(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)