package com.fullsteakdeveloper.news.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {
    companion object{
        fun getInterceptor() : OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return  okHttpClient
        }

        fun getRetrofit(): NewsService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NewsService::class.java)

        }





    }

}