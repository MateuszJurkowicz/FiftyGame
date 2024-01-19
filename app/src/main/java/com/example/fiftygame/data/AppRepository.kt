package com.example.fiftygame.data

import androidx.lifecycle.LiveData
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    val readAllGames: LiveData<List<Game>> = appDao.readAllGames()
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
    suspend fun addGame(game: Game) {
        appDao.addGame(game)
    }
    suspend fun updateGame(game: Game) {
        appDao.updateGame(game)
    }
    suspend fun deleteGame(game: Game) {
        appDao.deleteGame(game)
    }
    fun readAllGames(){
    }
    fun readGameWithFields(gameId: Int): LiveData<List<Field>> {
        return appDao.readGameWithFields(gameId)
    }
    fun searchDatabase(searchQuery: String): Flow<List<Field>> {
        return appDao.searchDatabase(searchQuery)
    }
}