@file:Suppress("NAME_SHADOWING")

package com.fullsteakdeveloper.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fullsteakdeveloper.news.adapter.AdapterNews
import com.fullsteakdeveloper.news.databinding.ActivityHomeBinding
import com.fullsteakdeveloper.news.models.ArticleNews
import com.fullsteakdeveloper.news.models.ResponseServer
import com.fullsteakdeveloper.news.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = intent
        val email = intent.getStringExtra("email")
        binding.hello.append("Hi, $email!")
        var categoryArticles :String = "bitcoint"

        binding.icSear.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        fun getApiArticles () {
            ConfigNetwork
                .getRetrofit()
                .getBitcointArticles(categoryArticles)
                .enqueue(object : Callback<ResponseServer>{
                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    binding.pgLoading.visibility = View.GONE
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

        getApiArticles()

        with(binding) {
            category1.setOnClickListener{
                categoryArticles = "bitcoint"
                binding.pgLoading.visibility = View.VISIBLE
                getApiArticles()
            }
            category2.setOnClickListener{
                categoryArticles = "tesla"
                binding.pgLoading.visibility = View.VISIBLE
                getApiArticles()
            }
            category3.setOnClickListener{
                categoryArticles = "apple"
                binding.pgLoading.visibility = View.VISIBLE
                getApiArticles()
            }
            category4.setOnClickListener{
                categoryArticles = "techcrunch"
                binding.pgLoading.visibility = View.VISIBLE
                getApiArticles()
            }
        }

    }


}