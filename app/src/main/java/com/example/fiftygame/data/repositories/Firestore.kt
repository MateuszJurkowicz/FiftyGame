package com.example.fiftygame.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class Firestore {
    private val db = Firebase.firestore
    fun addField(field: Field) {
        db.collection("fields")
            .add(field)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun addGame(game: Game) {
        db.collection("games")
            .add(Game())
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${game.pin}")
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
        db.collection("users").document(email.toString())
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${email}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun readAllGames(email: String?): LiveData<List<Game>> {
        val gamesLiveData = MutableLiveData<List<Game>>()

        db.collection("games").whereEqualTo("ownerEmail", email)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                val gamesList = ArrayList<Game>()
                for (docSnapshot in queryDocumentSnapshots) {
                    val game = docSnapshot.toObject(Game::class.java)
                    gamesList.add(Game(game.gameId, game.pin, game.description, game.ownerEmail))
                }
                gamesLiveData.value = gamesList
            }
            .addOnFailureListener { e ->
                Log.d(TAG, e.toString())
            }

        return gamesLiveData
    }

    fun deleteGame(game: Game) {
        //db.collection("games").document(game.id).delete()

    }
}