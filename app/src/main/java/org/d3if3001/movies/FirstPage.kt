package org.d3if3001.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.d3if3001.movies.databinding.FirstPageBinding

class FirstPage: AppCompatActivity() {
    private lateinit var binding: FirstPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            val intent = Intent(this@FirstPage,MainActivity::class.java)
            startActivity(intent)
        }
    }
}