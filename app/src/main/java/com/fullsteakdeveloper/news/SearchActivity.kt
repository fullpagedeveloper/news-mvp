package com.fullsteakdeveloper.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fullsteakdeveloper.news.adapter.AdapterNews
import com.fullsteakdeveloper.news.databinding.ActivitySearchBinding
import com.fullsteakdeveloper.news.models.ArticleNews
import com.fullsteakdeveloper.news.models.ResponseServer
import com.fullsteakdeveloper.news.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun getApiArticles () {
            val querySearch = binding.tiQuerySearch.text.toString()
            ConfigNetwork
                .getRetrofit()
                .getBitcointArticles(querySearch)
                .enqueue(object : Callback<ResponseServer> {
                    override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                        binding.pgLoading.visibility = View.GONE
                        Toast.makeText(applicationContext, "News not found", Toast.LENGTH_LONG).show()
                        Log.d("Internal server error", t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseServer>,
                        response: Response<ResponseServer>
                    ) {
                        binding.pgLoading.visibility = View.GONE
                        val status = response.body()?.status
                        if (status == "ok") {
                            val articles = response.body()?.articles
                            showNews(articles)
                        }
                    }

                    private fun showNews(articles: ArrayList<ArticleNews>?) {
                        val rv = findViewById<RecyclerView>(R.id.rvUser)
                        rv.adapter = AdapterNews(articles)
                    }
                })
        }

        binding.icSear.setOnClickListener {
            binding.pgLoading.visibility = View.VISIBLE
            getApiArticles()
        }

        binding.icBack.setOnClickListener {
            onBackPressed()
        }

    }
}