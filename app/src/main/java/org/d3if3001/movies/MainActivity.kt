package org.d3if3001.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3001.movies.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener{hitungDiskon()}
        binding.btnClear.setOnClickListener{reset()}

    }
    private fun reset() {
        binding.hargaInp.text?.clear()
        binding.diskonInp.text?.clear()
        binding.tvTotal.setText("")
    }
    private fun hitungDiskon() {
        val harga = binding.hargaInp.text.toString()
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(this, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val diskon = binding.diskonInp.text.toString()
        if (TextUtils.isEmpty(diskon)) {
            Toast.makeText(this, R.string.diskon_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val totalDiskon =  harga.toDouble() * diskon.toDouble() / 100
        val hargaBaru = harga.toDouble() - totalDiskon
        binding.tvTotal.text = getString(R.string.tvTotal_x,hargaBaru)
    }
}
