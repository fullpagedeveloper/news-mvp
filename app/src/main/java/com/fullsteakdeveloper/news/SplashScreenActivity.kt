package com.fullsteakdeveloper.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fullsteakdeveloper.news.databinding.ActivitySplasScreenPageBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplasScreenPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplasScreenPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}