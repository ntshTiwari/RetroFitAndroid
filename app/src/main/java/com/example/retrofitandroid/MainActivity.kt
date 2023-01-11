package com.example.retrofitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var newsListView: RecyclerView
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsListView = findViewById(R.id.newsList)

        newsListView.layoutManager = LinearLayoutManager(this)

        getNews()
    }

    fun getNews() {
        val newsInterface = NewsService.newsInterface

        newsInterface.getNews("in", 1).enqueue(
            object : Callback<News>{
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        newsAdapter = NewsAdapter(this@MainActivity, news.articles)

                        newsListView.adapter = newsAdapter

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