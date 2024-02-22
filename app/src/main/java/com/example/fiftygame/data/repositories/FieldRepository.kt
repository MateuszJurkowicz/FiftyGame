package com.example.fiftygame.data.repositories

import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.models.Field
import com.google.firebase.firestore.Query

class FieldRepository(private val firestore: Firestore) {


    fun addField(field: Field, gameId: String?) {
        firestore.addField(field, gameId)
    }

    fun updateField(field: Field, gameId: String?) {
        firestore.updateField(field, gameId)
    }

    fun deleteField(field: Field, gameId: String?) {
        firestore.deleteField(field, gameId)
    }

    fun deleteFieldsInGame(gameId: String) {
        firestore.deleteFieldsInGame(gameId)
    }

    fun searchDatabase(gameId: String?, searchQuery: String): Query {
        return firestore.searchDatabase(gameId, searchQuery)
    }

    fun readAllFields(gameId: String?): Query {
        return firestore.readAllFields(gameId)
    }


}