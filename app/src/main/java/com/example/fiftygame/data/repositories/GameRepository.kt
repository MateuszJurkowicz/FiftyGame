package com.example.fiftygame.data.repositories

import androidx.lifecycle.LiveData
import com.example.fiftygame.data.AppDao
import com.example.fiftygame.data.models.Game

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

    fun readGameWithPin(pin: Int): Game {
        return appDao.readGameWithPin(pin)
    }

}