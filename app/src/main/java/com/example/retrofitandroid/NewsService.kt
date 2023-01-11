package com.example.retrofitandroid

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NewsService {
    const val BASE_URL = "https://newsapi.org/v2/"

    val newsInterface: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInterface = retrofit.create(NewsInterface::class.java)
    }
}