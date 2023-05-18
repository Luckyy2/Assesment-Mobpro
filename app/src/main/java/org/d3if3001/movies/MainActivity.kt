package org.d3if3001.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3001.movies.databinding.ActivityMainBinding
import org.d3if3001.movies.model.DiskonViewModel
import org.d3if3001.movies.model.HasilDiskon

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: DiskonViewModel by lazy {
        ViewModelProvider(this)[DiskonViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener { hitungDiskon() }
        binding.btnClear.setOnClickListener { reset() }

        viewModel.hasilDiskon.observe(this) {
            showDiskon(it)
        }

    }

    private fun reset() {
        binding.namaInp.text?.clear()
        binding.hargaInp.text?.clear()
        binding.diskonInp.text?.clear()
        binding.tvNamaBarang.setText("")
        binding.tvTotal.setText("")
    }

    private fun hitungDiskon() {
        val nama = binding.namaInp.text.toString()
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }
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

        viewModel.hasilDiskon.value = HasilDiskon(diskon.toDouble(), harga.toDouble(), nama)
    }

    fun showDiskon(barang: HasilDiskon){
        binding.tvTotal.text =
            getString(R.string.tvTotal_x, viewModel.hitungDiskon(barang))

        binding.tvNamaBarang.text = barang.nama
    }
}


