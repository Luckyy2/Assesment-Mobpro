package org.d3if3001.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3001.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener {
            val harga = binding.hargaInp.text.toString().trim()
            val diskon = binding.diskonInp.text.toString().trim()
            var totalDiskon = 0
            var hargaBaru = 0

            when{
                TextUtils.isEmpty(harga) -> {
                    Toast.makeText(this,"Harga belum ada!", Toast.LENGTH_SHORT).show()
                    binding.hargaInp.requestFocus()
                }
                TextUtils.isEmpty(diskon) -> {
                    Toast.makeText(this,"Diskon belum ada!", Toast.LENGTH_SHORT).show()
                    binding.diskonInp.requestFocus()
                }
                else -> {
                    totalDiskon = harga.toInt() * diskon.toInt() / 100
                    hargaBaru = harga.toInt() - totalDiskon
                    binding.tvTotal.text = hargaBaru.toString()
                }
            }
        }
    }
}