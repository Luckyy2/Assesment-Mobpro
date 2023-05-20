package org.d3if3001.movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3001.movies.db.DiskonDao

class HitungDiskonViewModelFactory (
    private val db: DiskonDao
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DiskonViewModel::class.java)) {
                return DiskonViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}