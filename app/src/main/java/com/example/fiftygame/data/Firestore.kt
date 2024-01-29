package com.example.fiftygame.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore

class Firestore {
    private val db = Firebase.firestore
    fun addField(field: Field) {
        db.collection("fields_table")
            .add(field)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    fun addGame(game: Game) {
        db.collection("games_table")
            .add(game)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}