package com.example.fiftygame.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class Firestore {
    private val db = Firebase.firestore
    private val fieldsColRef = db.collection("fields")
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
                Log.w(TAG, "Error adding document", e)
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
                Log.w(TAG, "Error adding document", e)
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
                Log.w(TAG, "Error adding document", e)
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
        //return gamesColRef.document(gameId.toString()).collection("fields").orderBy("number")

    }

    private fun generateKeywords(name: String): List<String> {
        val keywords = mutableListOf<String>()
        for (i in 0 until name.length) {
            for (j in (i + 1)..name.length) {
                keywords.add(name.slice(i until j))
            }
        }
        return keywords
    }
}