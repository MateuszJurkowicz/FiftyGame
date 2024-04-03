package com.example.fiftygame.data.repositories;

import com.example.fiftygame.data.Firestore;
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.models.Player
import com.google.firebase.auth.FirebaseAuth

class UserRepository(private val firestore: Firestore) {
    fun addUser(userId: String, email: String?, name: String?) {
        firestore.addUser(userId, email, name)
    }

    suspend fun readPlayer(email: String?): Player {
        return firestore.readPlayer(email)
    }

    suspend fun readPlayerInGame(currentGame: Game, uid: String?): Player? {
        return firestore.readPlayerInGame(currentGame, uid)
    }
}
