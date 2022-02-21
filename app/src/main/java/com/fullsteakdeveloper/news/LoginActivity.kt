package com.fullsteakdeveloper.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fullsteakdeveloper.news.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email =  "news"//binding.tiEmail.text
        val password = "news"//binding.tiPassword.text

        val btnLoginSubmit = binding.btnLoginSubmit

        btnLoginSubmit.setOnClickListener {
            if (email.toString().isNotEmpty() && password.toString().isNotEmpty()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}