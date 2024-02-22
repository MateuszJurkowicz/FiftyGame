package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.repositories.Firestore
import com.example.fiftygame.data.models.Game
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    var readAllGames: Query
    private val firestore: Firestore = Firestore()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        readAllGames = firestore.readAllGames(mAuth.currentUser?.email)
    }
    fun addGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            firestore.addGame(game)
        }
    }

    suspend fun readGameWithPin(pin: Int): Game {
        return firestore.readGameWithPin(pin)
    }





}