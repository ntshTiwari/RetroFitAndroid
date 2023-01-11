package com.example.retrofitandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "5f5ca05a90be4f0da2b7f238673ea341"

interface NewsInterface {

    @GET("top-headlines?apiKey=$API_KEY")
    fun getNews(@Query("country") country: String, @Query("page") page: Int) : Call<News>
    /// calling this end point should call https://newsapi.org/v2/top-headlines?apiKey=API_KEY&country=country&page=page
}