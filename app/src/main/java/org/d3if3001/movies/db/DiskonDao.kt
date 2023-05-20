package org.d3if3001.movies.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiskonDao {

    @Insert
    fun insert(kalkulatordiskon: DiskonEntity)

    @Query("SELECT * FROM diskon ORDER BY id DESC")
    fun getLastDiskon(): LiveData<List<DiskonEntity>>

    @Query("DELETE FROM diskon")
    fun clearData()
}