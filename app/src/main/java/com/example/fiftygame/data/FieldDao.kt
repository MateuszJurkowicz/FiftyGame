package com.example.fiftygame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addField(field: Field)

    @Update
    suspend fun updateField(field: Field)

    @Delete
    suspend fun deleteField(field: Field)
    @Query("DELETE FROM fields_table")
    suspend fun deleteAllFields()

    @Query("SELECT * FROM fields_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Field>>

    @Query("SELECT * FROM fields_table WHERE entry LIKE :searchQuery OR question LIKE :searchQuery OR correctAnswer LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Field>>
}