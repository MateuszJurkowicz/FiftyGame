package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.models.Player
import com.example.fiftygame.data.repositories.GameRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    var readAllGames: Query
    private val gameRepository: GameRepository
    private val firestore: Firestore = Firestore()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        gameRepository = GameRepository(firestore, mAuth)
        readAllGames = gameRepository.readAllGames
    }
    fun addGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.addGame(game)
        }
    }

    suspend fun readGameWithPin(pin: Int): Game {
        return gameRepository.readGameWithPin(pin)
    }

    fun addPlayer(currentGame: Game, player: Player) {
        return gameRepository.addPlayer(currentGame, player)
    }


}