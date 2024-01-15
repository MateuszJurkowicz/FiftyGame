package com.example.fiftygame.data

import androidx.lifecycle.LiveData

class FieldRepository(private val fieldDao: FieldDao) {
    val readAllData: LiveData<List<Field>> = fieldDao.readAllData()
    suspend fun addField(field: Field) {
        fieldDao.addField(field);
    }

    suspend fun updateField(field: Field) {
        fieldDao.updateField(field)
    }
}