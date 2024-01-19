package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.AppDatabase
import com.example.fiftygame.data.AppRepository
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.models.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    val readAllGames: LiveData<List<Game>>
    private val repository: AppRepository
    private val firestore: Firestore = Firestore()

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = AppRepository(appDao)
        readAllGames = repository.readAllGames
    }

    fun addGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGame(game)
            firestore.addGame(game)
        }
    }

    fun updateGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGame(game)
        }
    }

    fun deleteGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGame(game)
        }
    }


    fun searchDatabase(searchQuery: String): LiveData<List<Field>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}