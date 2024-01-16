package com.example.fiftygame.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class FieldRepository(private val fieldDao: FieldDao) {
    val readAllData: LiveData<List<Field>> = fieldDao.readAllData()
    suspend fun addField(field: Field) {
        fieldDao.addField(field);
    }
    suspend fun updateField(field: Field) {
        fieldDao.updateField(field)
    }
    suspend fun deleteField(field: Field) {
        fieldDao.deleteField(field)
    }
    suspend fun deleteAllFields() {
        fieldDao.deleteAllFields()
    }
    fun searchDatabase(searchQuery: String): Flow<List<Field>> {
        return fieldDao.searchDatabase(searchQuery)
    }
}