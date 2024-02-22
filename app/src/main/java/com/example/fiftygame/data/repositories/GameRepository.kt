package com.example.fiftygame.data.repositories

import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.models.Game
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query

class GameRepository(private val firestore: Firestore, mAuth: FirebaseAuth) {
    var readAllGames: Query = firestore.readAllGames(mAuth.currentUser?.email)

    fun addGame(game: Game) {
        firestore.addGame(game)
    }

    suspend fun readGameWithPin(pin: Int): Game {
        return firestore.readGameWithPin(pin)
    }


}