package com.example.fiftygame.data

import androidx.lifecycle.LiveData
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.relations.GameWithFields
import kotlinx.coroutines.flow.Flow

class GameRepository(private val appDao: AppDao) {
    val readAllGames: LiveData<List<Game>> = appDao.readAllGames()
    suspend fun addGame(game: Game) {
        appDao.addGame(game)
    }
    suspend fun updateGame(game: Game) {
        appDao.updateGame(game)
    }
    suspend fun deleteGame(game: Game) {
        appDao.deleteGame(game)
    }
    fun readGameWithId(gameId: Int) :Game {
        return appDao.readGameWithId(gameId)
    }
    fun readAllGames(){
    }

}