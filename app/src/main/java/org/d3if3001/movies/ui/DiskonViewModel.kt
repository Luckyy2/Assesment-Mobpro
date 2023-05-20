package org.d3if3001.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3001.movies.db.DiskonDao
import org.d3if3001.movies.db.DiskonEntity
import org.d3if3001.movies.model.HasilDiskon
import org.d3if3001.movies.model.hitungDiskon

class DiskonViewModel(private val db: DiskonDao) : ViewModel() {


    private val hasilDiskon = MutableLiveData<HasilDiskon?>()

    fun hitungDiskon(harga: Double, diskon: Double) {
        val dataDiskon = DiskonEntity(
            harga = harga,
            diskon = diskon,
        )
        hasilDiskon.value = dataDiskon.hitungDiskon()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataDiskon)
            }
        }
    }

    fun getHasilDiskon(): LiveData<HasilDiskon?> = hasilDiskon
}