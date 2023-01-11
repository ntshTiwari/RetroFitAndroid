package com.example.retrofitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    fun getNews() {
        val newsInterface = NewsService.newsInterface

        newsInterface.getNews("in", 1).enqueue(
            object : Callback<News>{
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        news.articles.forEach {
                            Log.e("SUCCESS", it.title)
                        }
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.e("Error", t.toString())
                }

            }
        )
    }
}