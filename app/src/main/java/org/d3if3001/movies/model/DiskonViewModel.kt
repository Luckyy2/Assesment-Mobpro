package org.d3if3001.movies.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiskonViewModel : ViewModel() {

    val hasilDiskon: MutableLiveData<HasilDiskon> by lazy {
        MutableLiveData()
    }

    fun hitungDiskon(barang: HasilDiskon): Double {
        val totalDiskon =  barang.harga * barang.diskon / 100
        val hargaBaru = barang.harga - totalDiskon
        return hargaBaru
    }
}