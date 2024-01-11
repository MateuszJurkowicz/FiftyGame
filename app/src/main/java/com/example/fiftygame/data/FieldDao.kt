package com.example.fiftygame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FieldDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addField(field: Field)
    @Query("SELECT * FROM fields_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Field>>
}