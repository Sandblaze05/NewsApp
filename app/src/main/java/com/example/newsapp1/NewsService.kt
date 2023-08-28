package com.example.newsapp1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=709c58d171754ba9acf397a7b5649952

const val BASE_URL="https://newsapi.org/"
const val API_KEY="709c58d171754ba9acf397a7b5649952"

interface NewsInterface {

    @GET(/* value = */ "v2/top-headlines?apiKey=709c58d171754ba9acf397a7b5649952")
    fun getHeadlines(@Query(/* value = */ "country") country: String,@Query(/* value = */ "page") page: Int): Call<News>
}

object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}