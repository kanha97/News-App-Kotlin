package com.devkanhaiya.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.ArrayList
const val BASE_URL = "https://newsapi.org"
class MainActivity : AppCompatActivity(){
   private val  titleList = mutableListOf<String>()

   private val  authorList = mutableListOf<String>()

    private val  urlList =mutableListOf<String>()

    private val  urlimageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
    }
    private fun setUpRecyclerView() {

        recyclerView.layoutManager=GridLayoutManager(applicationContext,2)
        recyclerView.adapter = CustomAdapter(titleList,authorList,urlList,urlimageList)

    }
    private fun addToList(title: String ,author: String , url : String , urlimage: String){
        titleList.add(title)
        authorList.add(author)
        urlList.add(url)
        urlimageList.add(urlimage)
    }




    private fun fetchData(){

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APirequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {



            try {
                val response = api.getNews()

                for (article in response.articles) {
                    Log.i("MainActivity","response = $article")

                    addToList(article.title, article.author, article.url, article.urlToImage)
                }
                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                }
            }catch (e: Exception){
                Log.d("MainActivity","Error ${e.toString()}")
            }
        }



    }



}


