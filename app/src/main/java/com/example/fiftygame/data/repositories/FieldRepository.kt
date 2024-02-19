package com.example.fiftygame.data.repositories

import androidx.lifecycle.LiveData
import com.example.fiftygame.data.AppDao
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.relations.GameWithFields
import kotlinx.coroutines.flow.Flow

class FieldRepository(private val appDao: AppDao) {
    suspend fun addField(field: Field) {
        appDao.addField(field)
    }
    suspend fun addAllFields(fields: List<Field>) {
        appDao.addAllFields(fields)
    }

    suspend fun updateField(field: Field) {
        appDao.updateField(field)
    }

    suspend fun deleteField(field: Field) {
        appDao.deleteField(field)
    }

    suspend fun deleteFieldsInGame(gameId: Int) {
        appDao.deleteFieldsInGame(gameId)
    }


    fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>> {
        return appDao.readGameWithFields(gameId)
    }

    fun searchDatabase(gameId: Int, searchQuery: String): Flow<List<Field>> {
        return appDao.searchDatabase(gameId, searchQuery)
    }


}