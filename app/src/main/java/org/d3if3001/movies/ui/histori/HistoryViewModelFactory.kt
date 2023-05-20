package org.d3if3107.kalkulatordiskon.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3001.movies.db.DiskonDao

class HistoryViewModelFactory (
    private val db: DiskonDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}