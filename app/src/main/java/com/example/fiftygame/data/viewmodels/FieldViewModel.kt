package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.AppDatabase
import com.example.fiftygame.data.FieldRepository
import com.example.fiftygame.data.GameRepository
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.relations.GameWithFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel(application: Application, gameId: Int  ) : AndroidViewModel(application) {
    val readGameWithFields: LiveData<List<GameWithFields>>
    private val repository: FieldRepository
    private val firestore: Firestore = Firestore()

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = FieldRepository(appDao)
        readGameWithFields = repository.readGameWithFields(gameId)
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

    fun deleteAllFields() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFields()
        }
    }

    /*fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>> {
        return repository.readGameWithFields(gameId)
    }*/

    fun searchDatabase(searchQuery: String): LiveData<List<Field>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}