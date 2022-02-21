package com.fullsteakdeveloper.news.network

import com.fullsteakdeveloper.news.models.ResponseServer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {


    @GET("v2/everything?apiKey=133c93ffadb644b28495bc57b2a352ce")
    fun getBitcointArticles(@Query("q") q :String):Call<ResponseServer>
    
}