package com.example.retrofitandroid.RetrofitInterfaces

import com.example.retrofitandroid.models.QuoteList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    @GET("/quotes")
    suspend fun getQuotes(): Response<QuoteList>

//    @GET("/random/")
//    fun requestArtistInfo(@Query("tags") tag1: String,
////                          @Query("tag2") tag2: String
//    ): Call<QuoteList>
}