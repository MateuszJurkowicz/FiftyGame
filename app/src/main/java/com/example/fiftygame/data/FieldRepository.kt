package com.example.fiftygame.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.relations.GameWithFields
import kotlinx.coroutines.flow.Flow

class FieldRepository(private val appDao: AppDao) {
    suspend fun addField(field: Field) {
        appDao.addField(field)
    }
    suspend fun updateField(field: Field) {
        appDao.updateField(field)
    }
    suspend fun deleteField(field: Field) {
        appDao.deleteField(field)
    }
    suspend fun deleteAllFields() {
        appDao.deleteAllFields()
    }
    fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>> {
        Log.d("game id repository",gameId.toString())

        return appDao.readGameWithFields(gameId)
    }
    fun searchDatabase(searchQuery: String): Flow<List<Field>> {
        return appDao.searchDatabase(searchQuery)
    }
}