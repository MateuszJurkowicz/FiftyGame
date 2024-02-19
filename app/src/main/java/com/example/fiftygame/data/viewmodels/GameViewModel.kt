package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.AppDatabase
import com.example.fiftygame.data.repositories.GameRepository
import com.example.fiftygame.data.repositories.Firestore
import com.example.fiftygame.data.models.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GameRepository
    private val firestore: Firestore = Firestore()

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = GameRepository(appDao)
    }


    fun addGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.addGame(game)
            firestore.addGame(game)
        }
    }
    fun readAllGames(email: String?): LiveData<List<Game>> {
        return firestore.readAllGames(email)
    }

    fun updateGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.updateGame(game)
        }
    }

    fun deleteGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.deleteGame(game)
            firestore.deleteGame(game)
        }
    }

    fun readGameWithPin(pin: Int): Game {
        return repository.readGameWithPin(pin)
    }




}