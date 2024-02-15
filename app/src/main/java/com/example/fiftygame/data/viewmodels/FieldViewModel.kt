package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.AppDatabase
import com.example.fiftygame.data.repositories.FieldRepository
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.repositories.Firestore
import com.example.fiftygame.data.relations.GameWithFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FieldRepository
    private val firestore: Firestore = Firestore()

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = FieldRepository(appDao)
    }

    fun addField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addField(field)
            firestore.addField(field)
        }
    }

    fun updateField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateField(field)
        }
    }

    fun deleteField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteField(field)
        }
    }
    fun deleteFieldsInGame(gameId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFieldsInGame(gameId)
        }
    }

    fun deleteAllFields() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFields()
        }
    }

    fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>> {
        return repository.readGameWithFields(gameId)
    }

    fun searchDatabase(gameId: Int, searchQuery: String): LiveData<List<Field>> {
        return repository.searchDatabase(gameId, searchQuery).asLiveData()
    }


}