package com.example.retrofitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var gson = Gson();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var json = convertToJson(TestModel(1,"Test"))
        convertFromJson(json)
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