package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.repositories.FieldRepository
import com.example.fiftygame.data.Firestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel(application: Application) : AndroidViewModel(application) {
    private val fieldRepository: FieldRepository
    private val firestore: Firestore = Firestore()

    init {
        fieldRepository = FieldRepository(firestore)
    }
    fun addField(field: Field, gameId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            fieldRepository.addField(field, gameId)
        }
    }

    fun updateField(field: Field, gameId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            fieldRepository.updateField(field, gameId)
        }
    }

    fun deleteField(field: Field, gameId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            fieldRepository.deleteField(field, gameId)
        }
    }

    fun deleteFieldsInGame(gameId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fieldRepository.deleteFieldsInGame(gameId)
        }
    }


    fun readAllFields(gameId: String?): Query {
        return fieldRepository.readAllFields(gameId)
    }

    fun searchDatabase(gameId: String?, searchQuery: String): Query {
        return fieldRepository.searchDatabase(gameId, searchQuery)
    }


}