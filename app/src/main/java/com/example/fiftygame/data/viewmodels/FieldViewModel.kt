package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.repositories.Firestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel(application: Application) : AndroidViewModel(application) {
    private val firestore: Firestore = Firestore()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun addField(field: Field, gameId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            firestore.addField(field, gameId)
        }
    }
    fun addAllFields(fields: List<Field>) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.addAllFields(fields)
        }
    }

    fun updateField(field: Field, gameId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            firestore.updateField(field, gameId)
        }
    }

    fun deleteField(field: Field, gameId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            firestore.deleteField(field, gameId)
        }
    }
    fun deleteFieldsInGame(gameId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            firestore.deleteFieldsInGame(gameId)
        }
    }



    fun readAllFields(gameId: String?): Query {
        return firestore.readAllFields(gameId)
    }

    /*fun searchDatabase(gameId: Int, searchQuery: String): LiveData<List<Field>> {
        return repository.searchDatabase(gameId, searchQuery).asLiveData()
    }*/


}