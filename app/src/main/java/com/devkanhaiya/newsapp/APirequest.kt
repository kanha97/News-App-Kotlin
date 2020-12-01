package com.devkanhaiya.newsapp

import com.devkanhaiya.newsapp.API.NewsGsonApi
import retrofit2.http.GET

interface APirequest {
    @GET("/v2/top-headlines?country=in&category=technology&apiKey=a76d1362ad094e63b2c27cb8f0312a1d")
    suspend fun getNews(): NewsGsonApi
}