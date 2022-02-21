package com.fullsteakdeveloper.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fullsteakdeveloper.news.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = intent
        val author = intent.getStringExtra("author")
        val content = intent.getStringExtra("content")
        val description = intent.getStringExtra("description")
        val publishedAt = intent.getStringExtra("publishedAt")
        val title= intent.getStringExtra("title")
        val url= intent.getStringExtra("url")
        val urlToImage = intent.getStringExtra("urlToImage")

        binding.tvAuthorName.append(author)
        binding.tvContentNewsDetail.append(content)
        binding.tvSubtitleNewsDetail.append(description)
        binding.tvAuthorPublised.append(publishedAt.toString().slice(0..10))
        binding.tvTitleDetail.append(title)
        binding.tvUrlNewsDetail.append(url)

        Glide.with(this).load(urlToImage).into(binding.ivNewsImg)

        binding.ivBackPage.setOnClickListener {
            onBackPressed()
        }


    }
}