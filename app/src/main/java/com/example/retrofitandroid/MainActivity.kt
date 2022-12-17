package com.example.retrofitandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.retrofitandroid.RetrofitInterfaces.QuotesApi
import com.example.retrofitandroid.models.QuoteList
import com.example.retrofitandroid.models.TestModel
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    var gson = Gson();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var json = convertToJson(TestModel(1,"Test"))
        convertFromJson(json)

        requestArtistInfo()
    }

    fun getFromApi() {
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

//        GlobalScope.launch {
        lifecycleScope.launch {
            val result = quotesApi.getQuotes()
            println("result: ${result.body()}")

            println("result: ${result.body()?.count}")
            println("result: ${result.body()?.results}")
        }
    }

    fun requestArtistInfo() {
//        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
//
//        lifecycleScope.launch {
//            val call: Call<QuoteList> = quotesApi.requestArtistInfo("technology"
////                , "famous-quotes"
//            )
////            val execute = call.execute().body()
////            println("execute: $execute")
//
//            call.enqueue(
//                object : Callback<QuoteList>
//                {
//                @SuppressLint("SetTextI18n")
//                override fun onResponse(
//                    response: Response<QuoteList>,
//                    retrofit: Retrofit
//                ) {
//
//
//                }
//            }
//        }
    }

    private fun convertToJson(testModel: TestModel): String {
        var json = gson.toJson(testModel)
        println(json)
        return json
    }

    private fun convertFromJson(jsonString: String){
        var testModel = gson.fromJson(jsonString, TestModel::class.java)
        println(testModel);
    }
}