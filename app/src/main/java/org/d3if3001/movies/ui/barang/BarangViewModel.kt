package org.d3if3001.movies.ui.barang

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3001.movies.model.Barang
import org.d3if3001.movies.network.ApiStatus
import org.d3if3001.movies.network.BarangApi
import org.d3if3001.movies.network.UpdateWorker
import java.util.concurrent.TimeUnit

class BarangViewModel : ViewModel() {
    private val data = MutableLiveData<List<Barang>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(BarangApi.service.getBarang())
                val result = BarangApi.service.getBarang()
                status.postValue(ApiStatus.SUCCESS)
                Log.d("BarangViewModel", "Success: $result")
            } catch (e: Exception) {
                status.postValue(ApiStatus.FAILED)
                Log.d("BarangViewModel", "Failure: ${e.message}")
            }
        }
    }
    fun getData(): LiveData<List<Barang>> = data
    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}