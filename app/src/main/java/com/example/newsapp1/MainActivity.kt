package com.example.newsapp1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp1.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // setContentView(R.layout.activity_main)
        getNews()
    }

    private fun getNews() {
        val news=NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object :retrofit2.Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News?=response.body()
                if(news!=null){
                    Log.d("test",news.toString())
                    adapter= NewsAdapter(this@MainActivity,news.articles)
                    binding.contentRV.adapter=adapter
                    binding.contentRV.layoutManager=LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("test","err in fetching news",t)
            }
        })
    }
}