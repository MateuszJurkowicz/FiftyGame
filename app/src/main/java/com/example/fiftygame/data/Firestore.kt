package com.example.fiftygame.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.models.Player
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Firestore {
    private val db = Firebase.firestore
    private val gamesColRef = db.collection("games")
    private val usersColRef = db.collection("users")

    fun addField(field: Field, gameId: String?) {
        gamesColRef.document(gameId.toString()).collection("fields")
            .add(field)
            .addOnSuccessListener {
                field.fieldId = it.id
                field.keywords = generateKeywords(field.entry)
                gamesColRef.document(gameId.toString()).collection("fields").document(it.id)
                    .set(field)
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding field document", e)
            }
    }

    fun addGame(game: Game) {
        gamesColRef
            .add(game)
            .addOnSuccessListener {
                game.gameId = it.id
                gamesColRef.document(it.id)
                    .set(game)
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding game document", e)
            }
    }

    fun addUser(userId: String, email: String?, name: String?) {
        val user = hashMapOf(
            "userId" to userId,
            "email" to email,
            "name" to name
        )
        usersColRef.document(email.toString())
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: $email")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding user document", e)
            }
    }

    fun readAllGames(email: String?): Query {
        return gamesColRef.whereEqualTo("ownerEmail", email)
    }

    suspend fun readGameWithPin(pin: Int): Game {
        val game = gamesColRef
            .whereEqualTo("pin", pin)
            .limit(1)
            .get()
            .await()
            .toObjects(Game::class.java)
        return game[0]
    }

    fun readAllFields(gameId: String?): Query {
        return gamesColRef.document(gameId.toString()).collection("fields").orderBy("number")

    }

    fun updateField(field: Field, gameId: String?) {
        gamesColRef.document(gameId.toString()).collection("fields").document(field.fieldId)
            .set(field)

    }

    fun deleteFieldsInGame(gameId: String) {
        gamesColRef.document(gameId).collection("fields")
            .get()
            .addOnSuccessListener {
                for (doc in it) {
                    gamesColRef.document(gameId).collection("fields").document(doc.id).delete()
                }
            }

    }

    fun deleteField(field: Field, gameId: String?) {
        gamesColRef.document(gameId.toString()).collection("fields").document(field.fieldId).delete()
    }

    fun searchDatabase(gameId: String?, searchQuery: String): Query {
        return gamesColRef.document(gameId.toString()).collection("fields")
            .whereArrayContains("keywords", searchQuery)
            .limit(10)

    }

    private fun generateKeywords(name: String): List<String> {
        val keywords = mutableListOf<String>()
        for (i in name.indices) {
            for (j in (i + 1)..name.length) {
                keywords.add(name.slice(i until j))
            }
        }
        return keywords
    }

    suspend fun readPlayer(email: String?): Player {
        val querySnapshot = usersColRef
            .whereEqualTo("email", email)
            .get()
            .await()

        return querySnapshot.toObjects(Player::class.java).firstOrNull()!!
    }

    fun addPlayer(currentGame: Game, player: Player) {
        val gameId = currentGame.gameId.toString()
        val gameRef = gamesColRef.document(gameId)

        gameRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val gameData = documentSnapshot.toObject(Game::class.java)

                val updatedGamePlayers = gameData?.gamePlayers?.toMutableList()

                // Sprawdzenie, czy gracz jest już na liście
                val isPlayerOnList = updatedGamePlayers?.any { it.email == player.email }

                if (!isPlayerOnList!!) {
                    updatedGamePlayers.add(player)

                    gameRef.update("gamePlayers", updatedGamePlayers)
                        .addOnSuccessListener {
                            Log.d(TAG, "Player added successfully")
                        }
                        .addOnFailureListener { exception ->
                            Log.e(TAG, "Error adding player", exception)
                        }
                } else {
                    Log.e(TAG, "Player with email ${player.email} is already on the list")
                }
            } else {
                Log.e(TAG, "Game with ID $gameId does not exist")
            }
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error retrieving game", exception)
        }
    }



}