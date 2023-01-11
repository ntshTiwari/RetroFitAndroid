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

    var newsList = mutableListOf<Articles>()

    /// used for pagination
    var pageNo = 1;

    /// used to restrict calling fetch while fetch is happening
    var fetching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsListView = findViewById(R.id.newsList)

        newsAdapter = NewsAdapter(this@MainActivity, newsList)
        newsListView.adapter = newsAdapter

        var layoutManager = LinearLayoutManager(this)
        newsListView.layoutManager = layoutManager

        getNews()

        newsListView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                /// we know that total newslist is 38, so we hard coded it here
                /// we check if we are at the third last element, we fetch rest of the lists
                if (layoutManager.findLastCompletelyVisibleItemPosition() == newsList.size - 3 && newsList.size != 38) {
                    if(canFetch()){
                        Log.e("Paging:", "called")
                        getNews()
                    }
                }
            }
        })


    }

    fun getNews() {
        /// stop any new calls to fetch data
        blockFetchingData()

        val newsInterface = NewsService.newsInterface

        newsInterface.getNews("in", pageNo++).enqueue(
            object : Callback<News>{
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    /// start accepting new calls to fetch data
                    unBlockFetchingData()

                    val news = response.body()
                    if(news != null){
                        newsList.addAll(news.articles)
                        /// we notify our news adapter that data has changed
                        newsAdapter.notifyDataSetChanged()

                        Log.e("getNews:", pageNo.toString())
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    /// start accepting new calls to fetch data
                    unBlockFetchingData()

                    Log.e("Error", t.toString())
                }

            }
        )
    }

    /// functions for code clarity
    private fun blockFetchingData() {
        fetching = true
    }

    private fun unBlockFetchingData() {
        fetching = false
    }

    private fun canFetch(): Boolean {
        if(fetching) return false

        return true
    }
}