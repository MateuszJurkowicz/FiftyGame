package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.repositories.FieldRepository
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.repositories.Firestore
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel(application: Application) : AndroidViewModel(application) {
    private val firestore: Firestore = Firestore()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun addField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.addField(field)
            firestore.addField(field)
        }
    }
    fun addAllFields(fields: List<Field>) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.addAllFields(fields)
        }
    }

    fun updateField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.updateField(field)
        }
    }

    fun deleteField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.deleteField(field)
        }
    }
    fun deleteFieldsInGame(gameId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.deleteFieldsInGame(gameId)
        }
    }

    /*fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>> {
        return repository.readGameWithFields(gameId)
    }*/
    fun readUserGames() {

    }

    /*fun searchDatabase(gameId: Int, searchQuery: String): LiveData<List<Field>> {
        return repository.searchDatabase(gameId, searchQuery).asLiveData()
    }*/


}